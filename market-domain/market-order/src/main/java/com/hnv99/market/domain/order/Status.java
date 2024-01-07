package com.hnv99.market.domain.order;

import com.hnv99.market.domain.order.state.*;

public enum Status {
    ORDERED,
    PROCESSING,
    DELIVERING,
    DELIVERED,
    CANCELED,

    OrderState generateState() {
        return switch (this) {
            case ORDERED -> new Ordered();
            case PROCESSING -> new Processing();
            case DELIVERING -> new Delivering();
            case DELIVERED -> new Delivered();
            case CANCELED -> new Canceled();
        };
    }
}
