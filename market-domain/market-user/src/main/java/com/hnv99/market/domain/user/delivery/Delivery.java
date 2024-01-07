package com.hnv99.market.domain.user.delivery;

import com.hnv99.market.data.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicUpdate;

import java.util.regex.Pattern;

@Getter
@SuperBuilder
@NoArgsConstructor
@Entity
@DynamicUpdate
@Table(name = "deliveries")
public class Delivery extends BaseEntity {

    @Column(nullable = false)
    private Long userId;

    @Embedded
    private Address address;

    @Embedded
    private Info info;

    @Column(nullable = false)
    private boolean isDefault;


}
