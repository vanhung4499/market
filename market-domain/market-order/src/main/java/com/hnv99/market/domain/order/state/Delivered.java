package com.hnv99.market.domain.order.state;

import com.hnv99.market.common.exception.BaseException;
import com.hnv99.market.domain.order.Order;

import static com.hnv99.market.common.exception.ErrorCode.DELIVERED_ORDER;

public class Delivered implements OrderState {
    @Override
    public OrderState nextState(Order order) {
        throw new BaseException(DELIVERED_ORDER);
    }

    @Override
    public OrderState cancel(Order order) {
        throw new BaseException(DELIVERED_ORDER);
    }
}
