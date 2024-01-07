package com.hnv99.market.domain.order;

import jakarta.persistence.EntityManager;

public class CustomOrderRepositoryImpl implements CustomOrderRepository {
    private final EntityManager entityManager;

    public CustomOrderRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

}
