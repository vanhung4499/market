package com.hnv99.market.web.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import static com.hnv99.market.web.user.AddPayment.CreditRequest;
import static com.hnv99.market.web.user.AddPayment.MomoRequest;

public sealed interface AddPayment permits CreditRequest, MomoRequest {
    record CreditRequest(
            @NotBlank(message = "Please enter payment method information")
            @Schema(description = "Payment method information")
            String payInfo,

            @NotBlank(message = "Please enter the bank name")
            @Schema(description = "Bank name")
            String bank,

            @NotNull(message = "Please enter the expiration date of the credit card")
            @Schema(description = "Credit card expiration date")
            String expirationDate,

            @NotBlank(message = "Please enter the CVV of the credit card")
            @Schema(description = "Credit card CVV")
            String cvv
    ) implements AddPayment {
    }

    record MomoRequest(
            String phoneNumber
    ) implements AddPayment {
    }
}
