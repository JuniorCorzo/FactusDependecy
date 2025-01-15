package io.github.juniorcorzo.context.interfaces;

import io.github.juniorcorzo.dto.auth.ResponseAuthDTO;

public interface AuthContext {
    ResponseAuthDTO getAuthContext();
    void setAuthContext(ResponseAuthDTO authContext);
}
