package io.github.juniorcorzo.context.implementations;

import io.github.juniorcorzo.context.interfaces.AuthContext;
import io.github.juniorcorzo.dto.auth.ResponseAuthDTO;
import io.github.juniorcorzo.services.AuthService;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class InMemoryAuthContext implements AuthContext {
    private static volatile InMemoryAuthContext instance;
    private ResponseAuthDTO authContext;

    private InMemoryAuthContext() {
        log.info("Initialized auth context");
    }

    public static InMemoryAuthContext getInstance() {
        synchronized (InMemoryAuthContext.class) {
            if (instance == null) {
                instance = new InMemoryAuthContext();
            }
        }

        return instance;
    }

    @Override
    public ResponseAuthDTO getAuthContext() {
        if (this.authContext == null) new AuthService().authentication();
        return authContext;
    }

    @Override
    public void setAuthContext(ResponseAuthDTO authContext) {
        this.authContext = authContext;
    }

}
