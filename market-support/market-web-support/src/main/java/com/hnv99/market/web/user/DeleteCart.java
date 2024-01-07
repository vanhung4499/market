package com.hnv99.market.web.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.util.List;

import static com.hnv99.market.web.user.DeleteCart.Request;

public sealed interface DeleteCart permits Request {
    record Request(
            @NotNull(message = "Empty value is not allowed.")
            @Schema(description = "Cart ID list")
            List<Long> cartIds
    ) implements DeleteCart {
    }
}
