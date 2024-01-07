package com.hnv99.market.domain.user.payment;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
@Embeddable
public class CreditInfo {
    private String password;

    private Date expiredDate;
}
