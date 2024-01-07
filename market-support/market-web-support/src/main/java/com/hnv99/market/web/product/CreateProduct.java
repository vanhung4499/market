package com.hnv99.market.web.product;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import static com.hnv99.market.web.product.CreateProduct.Request;

public sealed interface CreateProduct permits Request {
    record Request(
            @NotNull(message = "The category ID cannot be null")
            @Schema(description = "The category ID")
            Long categoryId,

            @NotNull(message = "The product name cannot be null")
            @Schema(description = "The product name")
            String name,

            @Positive(message = "The price cannot be negative")
            @Schema(description = "The price")
            int price,

            @NotBlank(message = "The description cannot be blank")
            @Schema(description = "The description")
            String description,

            @NotBlank(message = "The seller cannot be blank")
            @Schema(description = "Seller information")
            String seller,

            @NotBlank(message = "The image URL cannot be blank")
            @Schema(description = "The image URL")
            String imageUrl

    ) implements CreateProduct {
    }
}
