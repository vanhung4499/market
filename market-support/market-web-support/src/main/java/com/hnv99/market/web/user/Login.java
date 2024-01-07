package com.hnv99.market.web.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

import static com.hnv99.market.web.user.Login.Request;
import static com.hnv99.market.web.user.Login.Response;

public sealed interface Login permits Request, Response {
    record Request(
            @NotBlank(message = "Please enter your login ID.")
            @Schema(description = "Login ID")
            String loginId,

            @NotBlank(message = "Please enter your password.")
            @Schema(description = "Login password")
            String password
    ) implements Login {
    }

    record Response(
            String token
    ) implements Login {
    }
}
