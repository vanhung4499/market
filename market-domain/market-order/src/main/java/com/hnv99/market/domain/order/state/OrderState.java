package com.hnv99.market.domain.order.state;

import com.hnv99.market.domain.order.Order;

public interface OrderState {
    OrderState nextState(Order order);
    OrderState cancel(Order order);
}
