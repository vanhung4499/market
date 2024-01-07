package com.hnv99.market.web.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import static com.hnv99.market.web.user.CreateCart.Request;

public sealed interface CreateCart permits Request {
    record Request(
            @NotNull(message = "Empty value is not allowed.")
            @Schema(description = "Product ID")
            Long productId,

            @Positive(message = "Negative value is not allowed.")
            @Schema(description = "Product quantity")
            int quantity
    ) implements CreateCart {
    }
}
