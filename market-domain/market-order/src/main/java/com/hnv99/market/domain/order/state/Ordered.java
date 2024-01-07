package com.hnv99.market.domain.order.state;

import com.hnv99.market.domain.order.Order;
import com.hnv99.market.domain.order.Status;

public class Ordered implements OrderState {
    @Override
    public OrderState nextState(Order order) {
        order.updateStatus(Status.PROCESSING);
        return new Processing();
    }

    @Override
    public OrderState cancel(Order order) {
        order.updateStatus(Status.CANCELED);
        return new Canceled();
    }
}
