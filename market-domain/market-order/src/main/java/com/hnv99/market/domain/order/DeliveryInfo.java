package com.hnv99.market.domain.order;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Embeddable
public class DeliveryInfo {
    @Column(length = 10, nullable = false)
    private String receiver;

    @Column(length = 30, nullable = false)
    private String phoneNumber;

    @Column(length = 50, nullable = false)
    private String address;

    @Column(length = 15, nullable = false)
    private String receiveArea;

    @Column(length = 30, nullable = false)
    private String entranceInfo;

    @Column(length = 10, nullable = false)
    private String packaging;
}
