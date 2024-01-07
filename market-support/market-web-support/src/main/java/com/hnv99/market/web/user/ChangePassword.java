package com.hnv99.market.web.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

import static com.hnv99.market.web.user.ChangePassword.Request;

public sealed interface ChangePassword permits Request {
    record Request(
            @NotBlank(message = "Please enter your old password.")
            @Schema(description = "Old password")
            String oldPassword,

            @NotBlank(message = "Please enter your new password.")
            @Schema(description = "New password")
            String newPassword
    ) implements ChangePassword {
    }
}
