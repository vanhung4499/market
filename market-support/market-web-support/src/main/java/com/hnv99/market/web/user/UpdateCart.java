package com.hnv99.market.web.user;

import static com.hnv99.market.web.user.UpdateCart.Request;
public sealed interface UpdateCart permits Request {
    record Request(
            Long productId,
            int quantity
    ) implements UpdateCart {
    }
}
