package com.hnv99.market.domain.service;

import com.hnv99.market.domain.order.Order;
import com.hnv99.market.domain.order.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional
    public void reviewOrderLine(Long id, int lineIndex) {
        Order order = findByIdOrThrow(id);
        order.reviewOrderLine(lineIndex);
    }
}
