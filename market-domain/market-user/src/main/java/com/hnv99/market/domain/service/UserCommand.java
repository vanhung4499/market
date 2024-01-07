package com.hnv99.market.domain.service;

import com.hnv99.market.domain.user.User;
import com.hnv99.market.domain.user.cart.Cart;
import com.hnv99.market.domain.user.cart.CartRepository;
import com.hnv99.market.domain.user.delivery.Delivery;
import com.hnv99.market.domain.user.delivery.DeliveryRepository;
import com.hnv99.market.domain.user.payment.Payment;
import com.hnv99.market.domain.user.payment.PaymentRepository;
import com.hnv99.market.web.user.AddPayment;
import com.hnv99.market.web.user.UpdateUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@AllArgsConstructor
@Transactional
public class UserCommand {
    private final PaymentRepository paymentRepository;
    private final DeliveryRepository deliveryRepository;
    private final CartRepository cartRepository;

    public void updateUserInfo(UpdateUser.Request request, String editPassword, User user) {
        user.update(request.name(), editPassword, request.sex(), request.birthday(), request.phoneNumber());
    }

    public void deletePayment(Payment payment) {
        payment.deletePayment();
    }

    public void updatePaymentPassword(User user, String encodedPassword) {
        user.updatePayPassword(encodedPassword);
    }

    public void addCart(Long id, Long productId, int quantity) {
        Cart cart = new Cart(id, productId, quantity);
        cartRepository.save(cart);
    }

    public void removeCartItem(Cart cart) {
        cartRepository.delete(cart);
    }

    public void removeCartItemList(List<Cart> cart) {
        cartRepository.deleteAllInBatch(cart);
    }

    public void updateQuantity(Cart cart, boolean isIncrease) {
        cart.updateQuantity(isIncrease);
    }
}
