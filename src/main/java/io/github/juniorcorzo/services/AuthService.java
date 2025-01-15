package io.github.juniorcorzo.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cdimascio.dotenv.Dotenv;
import io.github.juniorcorzo.context.implementations.InMemoryAuthContext;
import io.github.juniorcorzo.dto.auth.ResponseAuthDTO;
import io.github.juniorcorzo.enums.GrantType;
import io.github.juniorcorzo.interceptors.LoggerInterceptor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

@Slf4j
public class AuthService {
    private final InMemoryAuthContext authContext = InMemoryAuthContext.getInstance();

    private final OkHttpClient httpClient;
    private final Dotenv loadEnv;
    private final ObjectMapper mapper;

    public AuthService() {
        this.httpClient = new OkHttpClient.Builder()
                .addInterceptor(new LoggerInterceptor())
                .build();
        this.loadEnv = Dotenv.load();
        this.mapper = new ObjectMapper();
    }

    public void authentication() {
        MultipartBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("grant_type", GrantType.PASSWORD.name().toLowerCase())
                .addFormDataPart("client_id", this.loadEnv.get("CLIENT_ID"))
                .addFormDataPart("client_secret", this.loadEnv.get("CLIENT_SECRET"))
                .addFormDataPart("username", this.loadEnv.get("EMAIL"))
                .addFormDataPart("password", this.loadEnv.get("PASSWORD"))
                .build();

        this.setAuthContext(requestBody);
    }

    public void refreshToken() {
        MultipartBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("grant_type", GrantType.PASSWORD.name().toLowerCase())
                .addFormDataPart("client_id", this.loadEnv.get("CLIENT_ID"))
                .addFormDataPart("client_secret", this.loadEnv.get("CLIENT_SECRET"))
                .addFormDataPart("refresh_token", authContext.getAuthContext().refreshToken())
                .build();
        this.setAuthContext(requestBody);
    }

    private void setAuthContext(MultipartBody requestBody) {
        Request request = new Request.Builder()
                .url(String.format("%s/oauth/token", this.loadEnv.get("API_URL")))
                .post(requestBody)
                .build();

        try (Response response = this.httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code" + response);

            assert response.body() != null;
            ResponseAuthDTO authData = this.mapper.readValue(response.body().string(), ResponseAuthDTO.class);
            System.out.println(authData);
            this.authContext.setAuthContext(authData);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
