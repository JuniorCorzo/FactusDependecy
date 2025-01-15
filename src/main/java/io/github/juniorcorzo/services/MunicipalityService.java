package io.github.juniorcorzo.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cdimascio.dotenv.Dotenv;
import io.github.juniorcorzo.dto.ResponseDTO;
import io.github.juniorcorzo.dto.municipality.MunicipalityDTO;
import io.github.juniorcorzo.interceptors.AuthInterceptor;
import io.github.juniorcorzo.interceptors.LoggerInterceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.List;

public class MunicipalityService {
    private final OkHttpClient httpClient;

    public MunicipalityService() {
        this.httpClient = new OkHttpClient.Builder()
                .addInterceptor(new AuthInterceptor())
                .addInterceptor(new LoggerInterceptor())
                .build();
    }

    public List<MunicipalityDTO> getMunicipality(String name) {
        return this.sendRequest(name);
    }

    public List<MunicipalityDTO> getMunicipalities() {
        return this.sendRequest("");
    }

    private List<MunicipalityDTO> sendRequest(String name) {
        String url = Dotenv.load().get("API_URL");
        ObjectMapper mapper = new ObjectMapper();

        Request request = new Request.Builder()
                .url(String.format("%s/v1/municipalities?name=%s", url, name))
                .get()
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code" + response);

            assert response.body() != null;
            return mapper.readValue(response.body().string(), new TypeReference<ResponseDTO<MunicipalityDTO>>() {
                    })
                    .data();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
