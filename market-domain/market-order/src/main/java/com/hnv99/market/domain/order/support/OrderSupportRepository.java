package com.hnv99.market.domain.order.support;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderSupportRepository extends JpaRepository<OrderSupport, Long> {
    List<OrderSupport> findAllByUserId(Long userId);
    List<OrderSupport> findByOrderNumber(String orderNumber);
}
