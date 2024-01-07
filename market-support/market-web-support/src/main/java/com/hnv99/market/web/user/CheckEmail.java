package com.hnv99.market.web.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

import static com.hnv99.market.web.user.CheckEmail.Request;

public sealed interface CheckEmail permits Request {
    record Request(
            @NotBlank(message = "Please enter the email to check.")
            @Schema(description = "Email to check existence")
            String email
    ) implements CheckEmail {
    }
}
