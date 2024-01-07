package com.hnv99.market.domain.order.state;

import com.hnv99.market.domain.order.Order;

public class Delivering implements OrderState {
    @Override
    public OrderState nextState(Order order) {
        return new Delivered();
    }

    @Override
    public OrderState cancel(Order order) {
        return new Canceled();
    }
}
