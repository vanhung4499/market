package com.hnv99.market.domain.order;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@Embeddable
public class PaymentInfo {
    private static final int FREE_DELIVERY_STANDARD = 40000;
    private static final int BASIC_DELIVERY_FEE = 4000;
    private static final int DELIVERY_FREE = 0;

    @Column(nullable = false)
    private int totalPrice;

    @Column(nullable = false)
    private int deliveryFee;

    @Column(nullable = false)
    private int totalDiscount;

    @Column(nullable = false)
    private int actualPayAmount;

    // Reward Point

    @Column(nullable = false, length = 10)
    private String payment;

    public PaymentInfo(int totalPrice, int totalDiscount, String payment) {
        this.totalPrice = totalPrice;
        this.deliveryFee = calculateDeliveryFee(totalPrice);
        this.totalDiscount = totalDiscount;
        this.actualPayAmount = totalPrice - totalDiscount;
        this.payment = payment;
    }

    private int calculateDeliveryFee(int totalPrice) {
        return totalPrice > FREE_DELIVERY_STANDARD ? DELIVERY_FREE : BASIC_DELIVERY_FEE;
    }
}
