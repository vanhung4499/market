package com.hnv99.market.domain.user.delivery;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
    List<Delivery> findAllByUserId(Long userId);

    Optional<Delivery> findByIdAndUserId(Long deliveryId, Long userId);
}
