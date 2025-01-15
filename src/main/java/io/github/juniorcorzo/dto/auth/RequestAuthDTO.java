package io.github.juniorcorzo.dto.auth;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;

import java.io.Serializable;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Builder
public record RequestAuthDTO(
        String grantType,
        String clientId,
        String clientSecret,
        String username,
        String password
) implements Serializable {
}
