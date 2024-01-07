package com.hnv99.market.domain.user.cart;

import com.hnv99.market.common.exception.BaseException;
import com.hnv99.market.data.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.hnv99.market.common.exception.ErrorCode.NOT_CORRECT_QUANTITY;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "carts")
public class Cart extends BaseEntity {
    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long productId;

    @Column(nullable = false)
    private int quantity;

    public Cart(Long userId, Long productId, int quantity) {
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public void updateQuantity(boolean isIncrease) {
        if (isIncrease) {
            this.quantity += 1;
        } else {
            validQuantity();
            this.quantity -= 1;
        }
    }

    private void validQuantity() {
        if(1 >= this.quantity) {
            throw new BaseException(NOT_CORRECT_QUANTITY);
        }
    }
}
