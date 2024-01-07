package com.hnv99.market.web.product;

import static com.hnv99.market.web.product.FavoriteResponse.Get;

public sealed interface FavoriteResponse permits Get {
    record Get(
            Long productId,
            String imageUrl,
            String productName,
            int price
    ) implements FavoriteResponse {
    }
}
