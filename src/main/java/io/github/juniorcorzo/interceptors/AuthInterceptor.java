package io.github.juniorcorzo.interceptors;

import io.github.juniorcorzo.context.implementations.InMemoryAuthContext;
import io.github.juniorcorzo.dto.auth.ResponseAuthDTO;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

@Slf4j
public class AuthInterceptor implements Interceptor {

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        log.info("interceptor auth");
        ResponseAuthDTO authContext = InMemoryAuthContext.getInstance().getAuthContext();

        Request originalRequest = chain.request();
        Headers header = new Headers.Builder()
                .add("Authorization", String.format("%s %s", authContext.tokenType(), authContext.accessToken()))
                .add("Accept", "application/json")
                .build();

        return chain.proceed(originalRequest.newBuilder()
                .headers(header)
                .method(originalRequest.method(), originalRequest.body())
                .build()
        );
    }

}
