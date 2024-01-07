package com.hnv99.market.domain.service;

import com.hnv99.market.common.exception.BaseException;
import com.hnv99.market.domain.user.User;
import com.hnv99.market.domain.user.UserRepository;
import com.hnv99.market.domain.user.cart.Cart;
import com.hnv99.market.domain.user.delivery.Delivery;
import com.hnv99.market.domain.user.delivery.DeliveryRepository;
import com.hnv99.market.domain.user.payment.Payment;
import com.hnv99.market.domain.user.payment.PaymentRepository;
import com.hnv99.market.domain.user.cart.CartRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.hnv99.market.common.exception.ErrorCode.*;

@Component
@AllArgsConstructor
@Transactional(readOnly = true)
public class UserQuery {
    private final UserRepository userRepository;
    private final DeliveryRepository deliveryRepository;
    private final PaymentRepository paymentRepository;
    private final CartRepository cartRepository;

    public User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> BaseException.withId(NOT_EXISTS_USER, userId));
    }

    public List<Delivery> getAllAddress(Long userId) {
        return deliveryRepository.findAllByUserId(userId);
    }

    public Delivery findAddress(Long userId, Long deliveryId) {
        return deliveryRepository.findByIdAndUserId(deliveryId, userId)
                .orElseThrow(() -> BaseException.withId(ADDRESS_NOT_FOUND, deliveryId));
    }

    public List<String> getAllPayments(Long userId) {
        return paymentRepository.findAllByUserId(userId);
    }

    public Payment getPayment(Long userId, Long paymentId) {
        return paymentRepository.findByUserIdAndId(userId, paymentId)
                .orElseThrow(() -> BaseException.withId(PAYMENT_NOT_FOUND, paymentId));
    }

    @Transactional
    public Cart getCart(Long cartId) {
        return this.cartRepository.findById(cartId)
                .orElseThrow(() -> BaseException.withId(CART_NOT_FOUND, cartId));
    }

    @Transactional
    public List<Cart> getAllCart(Long userId) {
        return this.cartRepository.findAllByUserId(userId);
    }
}
