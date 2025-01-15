package io.github.juniorcorzo.interceptors;


import lombok.extern.slf4j.Slf4j;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

@Slf4j
public class LoggerInterceptor implements Interceptor {
    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request request = chain.request();

        long t1 = System.nanoTime();
        log.info("Sending request {} on {}\n{}", request.url(), chain.connection(), request.headers());

        Response response = chain.proceed(request);

        long t2 = System.nanoTime();
        log.info("Received response for {} in {}\n{}",
                response.request().url(), (t2 - t1) / 1e6d, response.headers());

        return response;

    }
}
