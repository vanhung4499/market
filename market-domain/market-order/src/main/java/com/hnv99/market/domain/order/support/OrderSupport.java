package com.hnv99.market.domain.order.support;

import com.hnv99.market.common.exception.BaseException;
import com.hnv99.market.data.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

import static com.hnv99.market.common.exception.ErrorCode.NOT_AUTHOR;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "order_supports")
public class OrderSupport extends BaseEntity {
    public enum Status {
        PREPARE,
        ANSWERED,
        DELETED
    }

    public enum Type {
        DELIVERY,
        MISSING,
        PRODUCT,
        ORDER,
        EVENT,
        ETC
    }

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long orderId;

    @Column(nullable = false)
    private String orderNumber;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private Type type;

    @Column(nullable = false, length = 30)
    private String title;

    @Column(nullable = false, columnDefinition = "text")
    private String content;

    @Column(columnDefinition = "text")
    private String answerContent;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    public void updateOrderSupport(String title, String content) {
        this.title = title;
        this.content = content;
        changeUpdatedDate();
    }

    public void validateAuthor(Long userId) {
        if (!Objects.equals(this.userId, userId)) {
            throw BaseException.withId(NOT_AUTHOR, userId);
        }
    }
}
