package com.hnv99.market.domain.order.state;

import com.hnv99.market.domain.order.Order;
import com.hnv99.market.domain.order.Status;

public class Processing implements OrderState {
    @Override
    public OrderState nextState(Order order) {
        order.updateStatus(Status.DELIVERING);
        return new Delivering();
    }

    @Override
    public OrderState cancel(Order order) {
        order.updateStatus(Status.CANCELED);
        return new Canceled();
    }
}
