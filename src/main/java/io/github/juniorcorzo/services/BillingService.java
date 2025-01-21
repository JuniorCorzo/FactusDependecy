package io.github.juniorcorzo.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cdimascio.dotenv.Dotenv;
import io.github.juniorcorzo.dto.ResponseDTO;
import io.github.juniorcorzo.dto.SingleDataResponseDTO;
import io.github.juniorcorzo.dto.billing.request.BillFiltersRequestDTO;
import io.github.juniorcorzo.dto.billing.request.BillingRequestDTO;
import io.github.juniorcorzo.dto.billing.response.BillEventsResponseDTO;
import io.github.juniorcorzo.dto.billing.response.BillFileDTO;
import io.github.juniorcorzo.dto.billing.response.BillingResponseDTO;
import io.github.juniorcorzo.dto.billing.response.BillsFilteredResponseDTO;
import io.github.juniorcorzo.interceptors.AuthInterceptor;
import io.github.juniorcorzo.interceptors.LoggerInterceptor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

@Slf4j
public class BillingService {
    private static final String API_URL = Dotenv.load().get("API_URL");
    private static final MediaType JSON = MediaType.get("application/json");
    private final OkHttpClient httpClient;
    private final ObjectMapper mapper;

    public BillingService() {
        this.httpClient = new OkHttpClient.Builder()
                .addInterceptor(new AuthInterceptor())
                .addInterceptor(new LoggerInterceptor())
                .build();

        mapper = new ObjectMapper();
    }

    public BillsFilteredResponseDTO getAllBills() {
        return this.filterBills(new BillFiltersRequestDTO("", "", "", "", "", ""));
    }

    public BillsFilteredResponseDTO filterBills(BillFiltersRequestDTO filters) {
        Request request = new Request.Builder()
                .url(String.format("%s/v1/bills?filter[identification]=%s&filter[names]=%s&filter[number]=%s&filter[prefix]=%s&filter[reference_code]=%s&filter[status]=%s",
                        API_URL, filters.identification(), filters.names(), filters.number(), filters.prefix(), filters.referenceCode(), filters.status()))
                .get()
                .build();

        try (Response response = this.httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code" + response.body());

            assert response.body() != null;
            return this.mapper.readValue(
                    response.body().string(),
                    new TypeReference<ResponseDTO<BillsFilteredResponseDTO>>() {
                    }).data().getFirst();
        } catch (IOException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }

    public BillingResponseDTO getBill(String number) {
        Request request = new Request.Builder()
                .url(String.format("%s/v1/bills/show/%s", API_URL, number))
                .get()
                .build();

        try (Response response = this.httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code" + response.body());

            assert response.body() != null;
            return this.mapper.readValue(response.body().string(), new TypeReference<SingleDataResponseDTO<BillingResponseDTO>>() {
                    })
                    .data();
        } catch (IOException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }

    public BillEventsResponseDTO getBillEvents(String number) {
        Request request = new Request.Builder()
                .url(String.format("%s//v1/bills/%s/radian/events", API_URL, number))
                .get()
                .build();

        try (Response response = this.httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code" + response.body());

            assert response.body() != null;
            return this.mapper.readValue(response.body().string(), new TypeReference<ResponseDTO<BillEventsResponseDTO>>() {
            }).data().getFirst();
        } catch (IOException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }

    public void downloadBillPdf(String number) {
        Request request = new Request.Builder()
                .url(String.format("%s/v1/bills/download-pdf/%s", API_URL, number))
                .get()
                .build();

        try (Response response = this.httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code" + response.body());

            assert response.body() != null;
            String bodyResponse = response.body().string();
            BillFileDTO billFile = this.mapper.readValue(bodyResponse, new TypeReference<SingleDataResponseDTO<BillFileDTO>>() {
            }).data();

            try (FileOutputStream fos = new FileOutputStream("E:\\FactusDependency\\src\\main\\resources\\".concat(billFile.fileName()).concat(".pdf"))) {
                fos.write(Base64.getDecoder().decode(billFile.pdfBase64Encoded()));
            }
        } catch (IOException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }

    public BillFileDTO getBillPdf(String number) {
        Request request = new Request.Builder()
                .url(String.format("%s/v1/bills/download-pdf/%s", API_URL, number))
                .get()
                .build();

        try (Response response = this.httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code" + response.body());

            assert response.body() != null;
            return this.mapper.readValue(response.body().string(), new TypeReference<ResponseDTO<BillFileDTO>>() {
            }).data().getFirst();
        } catch (IOException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }

    public void downloadBillXml(String number) {
        Request request = new Request.Builder()
                .url(String.format("%s/v1/bills/download-xml/%s", API_URL, number))
                .get()
                .build();

        try (Response response = this.httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code" + response.body());

            assert response.body() != null;
            BillFileDTO billFile = this.mapper.readValue(response.body().string(), new TypeReference<ResponseDTO<BillFileDTO>>() {
            }).data().getFirst();

            try (FileOutputStream fos = new FileOutputStream("E:\\FactusDependency\\src\\main\\resources\\".concat(billFile.fileName()).concat(".pdf"))) {
                fos.write(Base64.getDecoder().decode(billFile.xmlBase64Encoded()));
            }
        } catch (IOException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }

    public BillFileDTO getBillXml(String number) {
        Request request = new Request.Builder()
                .url(String.format("%s/v1/bills/download-xml/%s", API_URL, number))
                .get()
                .build();

        try (Response response = this.httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code" + response.body());

            assert response.body() != null;
            return this.mapper.readValue(response.body().string(), new TypeReference<ResponseDTO<BillFileDTO>>() {
            }).data().getFirst();
        } catch (IOException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }

    public BillingResponseDTO createAndValidateBill(BillingRequestDTO billingRequest) {
        try {
            Request request = new Request.Builder()
                    .url(String.format("%s/v1/bills/validate", API_URL))
                    .post(RequestBody.create(mapper.writeValueAsString(billingRequest), JSON))
                    .build();

            try (Response response = this.httpClient.newCall(request).execute()) {
                assert response.body() != null;
                if (!response.isSuccessful()) throw new IOException("Unexpected code" + response.body().string());
                return this.mapper.readValue(response.body().string(),
                                new TypeReference<SingleDataResponseDTO<BillingResponseDTO>>() {
                                })
                        .data();
            }
        } catch (IOException e) {
            log.error(e.getMessage());
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }

    public void deleteBill(String referenceCode) {
        Request request = new Request.Builder()
                .url(String.format("%s//v1/bills/destroy/reference/%s", API_URL, referenceCode))
                .delete()
                .build();

        try (Response response = this.httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code" + response.body());
            log.info("Bill whit reference code {} delete successfully", referenceCode);
        } catch (IOException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }

}
