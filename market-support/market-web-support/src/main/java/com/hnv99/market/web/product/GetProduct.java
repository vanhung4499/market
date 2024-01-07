package com.hnv99.market.web.product;

import static com.hnv99.market.web.product.GetProduct.Response;

public sealed interface GetProduct permits Response {
    record Response(
            String imageUrl,
            String delivery,
            String productName,
            String description,
            int price,
            Long reviewCount,
            String status
    ) implements GetProduct {
    }
}
