package io.github.juniorcorzo.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cdimascio.dotenv.Dotenv;
import io.github.juniorcorzo.dto.ResponseDTO;
import io.github.juniorcorzo.dto.tributes.TributesDTO;
import io.github.juniorcorzo.interceptors.AuthInterceptor;
import io.github.juniorcorzo.interceptors.LoggerInterceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.List;

public class TributesService {
    private final OkHttpClient httpClient;

    public TributesService() {
        httpClient = new OkHttpClient.Builder()
                .addInterceptor(new AuthInterceptor())
                .addInterceptor(new LoggerInterceptor())
                .build();
    }

    public List<TributesDTO> getTribute(String name) {
        return this.sendRequest(name);
    }

    public List<TributesDTO> getTributes() {
        return this.sendRequest("");
    }

    private List<TributesDTO> sendRequest(String name) {
        ObjectMapper mapper = new ObjectMapper();
        String api_url = Dotenv.load().get("API_URL");
        Request request = new Request.Builder()
                .url(String.format("%s/v1/tributes/products?name=%s", api_url, name))
                .get()
                .build();

        try (Response response = this.httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code" + response);

            assert response.body() != null;
            return mapper.readValue(response.body().string(), new TypeReference<ResponseDTO<TributesDTO>>() {
                    })
                    .data();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
