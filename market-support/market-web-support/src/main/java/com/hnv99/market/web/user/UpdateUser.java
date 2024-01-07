package com.hnv99.market.web.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

import static com.hnv99.market.web.user.UpdateUser.Request;

public sealed interface UpdateUser permits Request {
    record Request(
            @NotBlank(message = "Please enter your name.")
            @Schema(description = "Name")
            String name,

            @NotBlank(message = "Please enter your phone number.")
            @Schema(description = "Phone number")
            String phoneNumber,

            @NotBlank(message = "Please enter your sex.")
            @Schema(description = "Sex", allowableValues = {"male", "female", "other"})
            String sex,

            @NotBlank(message = "Please enter your birthday.")
            @Schema(description = "Birthday")
            LocalDate birthday

    ) implements UpdateUser {
    }
}
