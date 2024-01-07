package com.hnv99.market.web.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

import static com.hnv99.market.web.user.ChangePayPassword.Request;

public sealed interface ChangePayPassword permits Request {
    record Request(
            @NotBlank(message = "Please enter your new password.")
            @Schema(description = "New password")
            String password
    ) implements ChangePayPassword {
    }
}
