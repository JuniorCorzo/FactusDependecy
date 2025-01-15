package io.github.juniorcorzo.tasks;

import io.github.juniorcorzo.services.AuthService;

public class InitializedAuthContextTask implements Runnable {
    @Override
    public void run() {
        new AuthService().authentication();
    }
}

