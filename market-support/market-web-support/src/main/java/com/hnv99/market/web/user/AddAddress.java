package com.hnv99.market.web.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import static com.hnv99.market.web.user.AddAddress.Request;

public sealed interface AddAddress permits Request {
    record Request(
            @NotBlank(message = "Please enter your address.")
            @Length(max = 100)
            @Schema(description = "Road address")
            String roadAddress
    ) implements AddAddress {
    }
}
