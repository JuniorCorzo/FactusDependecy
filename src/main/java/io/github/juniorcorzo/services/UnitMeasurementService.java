package io.github.juniorcorzo.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cdimascio.dotenv.Dotenv;
import io.github.juniorcorzo.dto.ResponseDTO;
import io.github.juniorcorzo.dto.measurement.MeasurementUnitsDTO;
import io.github.juniorcorzo.interceptors.AuthInterceptor;
import io.github.juniorcorzo.interceptors.LoggerInterceptor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.List;

@Slf4j
public class UnitMeasurementService {
    private final OkHttpClient httpClient;

    public UnitMeasurementService() {
        httpClient = new OkHttpClient.Builder()
                .addInterceptor(new AuthInterceptor())
                .addInterceptor(new LoggerInterceptor())
                .build();
    }

    public List<MeasurementUnitsDTO> getMeasurementUnits() {
        return this.sendRequest("");
    }

    public List<MeasurementUnitsDTO> getMeasurementUnits(String name) {
        return this.sendRequest(name);
    }

    private List<MeasurementUnitsDTO> sendRequest(String name) {
        ObjectMapper mapper = new ObjectMapper();
        final String API_URL = Dotenv.load().get("API_URL");

        Request request = new Request.Builder()
                .url(String.format("%s/v1/measurement-units?name=%s", API_URL, name))
                .get()
                .build();

        try (Response response = this.httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected Code" + response);

            assert response.body() != null;
            return mapper.readValue(response.body().string(), new TypeReference<ResponseDTO<MeasurementUnitsDTO>>() {
                    })
                    .data();
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

}
