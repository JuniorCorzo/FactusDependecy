package io.github.juniorcorzo.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cdimascio.dotenv.Dotenv;
import io.github.juniorcorzo.dto.ResponseDTO;
import io.github.juniorcorzo.dto.numberingRange.NumberingRangeDTO;
import io.github.juniorcorzo.dto.numberingRange.NumberingRangeFiltersDTO;
import io.github.juniorcorzo.interceptors.AuthInterceptor;
import io.github.juniorcorzo.interceptors.LoggerInterceptor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

@Slf4j
public class NumberingRangeService {
    private static final MediaType JSON = MediaType.get("application/json");
    private static final String API_URL = Dotenv.load().get("API_URL");
    private final ObjectMapper mapper;
    private final OkHttpClient httpClient;

    public NumberingRangeService() {
        mapper = new ObjectMapper();
        httpClient = new OkHttpClient.Builder()
                .addInterceptor(new AuthInterceptor())
                .addInterceptor(new LoggerInterceptor())
                .build();
    }

    public List<NumberingRangeDTO> getNumberingRanges() {
        return this.sendRequest(new NumberingRangeFiltersDTO("", "", "", "", ""));
    }

    public List<NumberingRangeDTO> getNumberingRange(NumberingRangeFiltersDTO filters) {
        return this.sendRequest(filters);
    }

    public void createRange(NumberingRangeDTO numberingRange) {
        try {
            Request request = new Request.Builder()
                    .url(String.format("%s/v1/numbering-ranges", API_URL))
                    .post(RequestBody.create(this.mapper.writeValueAsString(numberingRange), JSON))
                    .build();
            try (Response response = this.httpClient.newCall(request).execute()) {
                if (!response.isSuccessful()) throw new IOException("Unexpected code" + response);
                log.info("Numbering range created successfully");
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    public void updateRange(@NotNull NumberingRangeDTO numberingRange) {
        try {
            Request request = new Request.Builder()
                    .url(String.format("%s//v1/numbering-ranges/%d", API_URL, numberingRange.id()))
                    .put(RequestBody.create(this.mapper.writeValueAsBytes(numberingRange), JSON))
                    .build();

            try (Response response = this.httpClient.newCall(request).execute()) {
                if (!response.isSuccessful()) throw new IOException("Unexpected code" + response);
                log.info("Update numbering range is successfully");
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }

    }

    public void deleteRange(int idRange) {
        Request request = new Request.Builder()
                .url(String.format("%s//v1/numbering-ranges/%d", API_URL, idRange))
                .delete()
                .build();

        try (Response response = this.httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code" + response);
            log.info("Delete numbering range is successfully");
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private List<NumberingRangeDTO> sendRequest(@NotNull NumberingRangeFiltersDTO filters) {
        Request request = new Request.Builder()
                .url(String.format(
                        "%s/v1/numbering-ranges?filter[id]=%s&filter[document]=%s&filter[resolution_number]=%s&filter[technical_key]=%s&filter[is_active]=%s",
                        API_URL, filters.id(), filters.document(), filters.resolutionNumber(), filters.technicalKey(), filters.isActive())
                )
                .get()
                .build();

        try (Response response = this.httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code" + response);

            assert response.body() != null;
            return mapper.readValue(response.body().string(), new TypeReference<ResponseDTO<NumberingRangeDTO>>() {
                    })
                    .data();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
