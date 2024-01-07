package com.hnv99.market.web.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

import static com.hnv99.market.web.common.UserRegex.*;
import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.NOT_REQUIRED;
import static com.hnv99.market.web.user.Register.Request;

public sealed interface Register permits Request {
    record Request(
            @NotBlank(message = "Please enter your login ID.")
            @Schema(description = "Login ID")
            String loginId,

            @NotBlank(message = "Please enter your password.")
            @Schema(description = "Password")
            String password,

            @NotBlank(message = "Please enter your email.")
            @Pattern(regexp = EMAIL_REGEXP, message = "Please enter a valid email.")
            @Schema(description = "Email")
            String email,

            @NotBlank(message = "Please enter your name.")
            @Pattern(regexp = USER_NAME_REGEXP, message = "Please enter a valid name.")
            @Schema(description = "Name")
            String name,

            @NotBlank(message = "Please enter your phone number.")
            @Pattern(regexp = PHONE_NUMBER_REGEXP, message = "Please enter a valid phone number.")
            @Schema(description = "Phone number")
            String phoneNumber,

            @NotBlank(message = "Please enter your sex.")
            @Schema(description = "Sex")
            String sex,

            @NotBlank(message = "Please enter your birthday.")
            @Schema(description = "Birthday")
            LocalDate birthday,

            @Schema(description = "Referral", requiredMode = NOT_REQUIRED)
            String referral,

            @NotBlank(message = "Please enter your address.")
            @Length(max = 100)
            @Schema(description = "Address")
            String address

    ) implements Register {
    }
}
