package io.github.juniorcorzo.tasks;

import io.github.juniorcorzo.services.AuthService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RefreshTokenTask implements Runnable {

    @Override
    public void run() {
        log.info("Starting token refresh process");
        new AuthService().refreshToken();
        log.info("Ending token refresh process");
    }
}
