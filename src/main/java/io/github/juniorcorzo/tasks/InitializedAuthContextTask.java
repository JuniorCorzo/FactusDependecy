package io.github.juniorcorzo.tasks;

import io.github.juniorcorzo.context.implementations.InMemoryAuthContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InitializedAuthContextTask implements Runnable {
    @Override
    public void run() {
        log.info("Initializing Auth Context Process");
        InMemoryAuthContext.getInstance().getAuthContext();
        log.info("Auth Context Initialization Process Completed");
    }
}

