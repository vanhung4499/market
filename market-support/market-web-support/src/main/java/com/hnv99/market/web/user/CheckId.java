package com.hnv99.market.web.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

import static com.hnv99.market.web.user.CheckId.Request;

public sealed interface CheckId permits Request {
    record Request(
            @NotBlank(message = "Please enter the ID to check.")
            @Schema(description = "ID to check existence")
            String loginId
    ) implements CheckId {
    }
}
