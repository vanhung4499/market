package com.hnv99.market.domain.order;

import com.hnv99.market.common.exception.BaseException;
import com.hnv99.market.data.BaseEntity;
import com.hnv99.market.domain.order.state.OrderState;
import com.hnv99.market.domain.order.state.Ordered;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import static com.hnv99.market.common.exception.ErrorCode.INVALID_ORDER_LINE_INDEX;
import static com.hnv99.market.common.exception.ErrorCode.NOT_OWNER;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order extends BaseEntity {
    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyMMddss");
    private static final Random random = new Random();
    private static final int RANDOM_BOUND = 10000;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false, unique = true)
    private String orderNumber;

    @ElementCollection
    @CollectionTable(name = "order_lines", joinColumns = @JoinColumn(name = "order_id"))
    @OrderColumn(name = "line_index")
    private List<OrderLine> orderLines = new ArrayList<>();

    @Embedded
    private PaymentInfo paymentInfo;

    @Embedded
    private DeliveryInfo deliveryInfo;

    @Column
    private LocalDateTime deliveredAt;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Transient
    private OrderState orderState;

    public Order(Long userId, List<OrderLine> orderLines, PaymentInfo paymentInfo, DeliveryInfo deliveryInfo) {
        this(userId, orderLines, paymentInfo, deliveryInfo, null, Status.ORDERED, new Ordered());
    }

    public void nextState() {
        if (orderState == null) {
            this.orderState = status.generateState();
        }

        this.orderState = orderState.nextState(this);
    }

    public void cancel() {
        this.orderState = orderState.cancel(this);
    }

    public void updateStatus(Status status) {
        this.status = status;
    }

    public void delivered() {
        updateStatus(Status.DELIVERED);
        this.deliveredAt = LocalDateTime.now();
    }

    private String generateOrderNumber() {
        String currentDate = LocalDateTime.now().format(dateFormat);
        int randomDigits = random.nextInt(RANDOM_BOUND);

        return currentDate + randomDigits;
    }

    public void reviewOrderLine(int lineIndex) {
        validateOrderLineIndex(lineIndex);
        OrderLine orderLine = orderLines.get(lineIndex);
        orderLine.reviewed();
    }

    private void validateOrderLineIndex(int lineIndex) {
        if (orderLines.size() <= lineIndex) {
            throw new BaseException(INVALID_ORDER_LINE_INDEX);
        }
    }

    public String summarizeOrderLines() {
        int size = orderLines.size();
        String firstLineName = orderLines.getFirst().getProductName();

        return size != 1 ? String.format("%s and %d items", firstLineName, size - 1) : firstLineName;
    }

    public void validateOrdersOwner(Long userId) {
        if (!Objects.equals(this.userId, userId)) {
            throw BaseException.withId(NOT_OWNER, userId);
        }
    }
}
