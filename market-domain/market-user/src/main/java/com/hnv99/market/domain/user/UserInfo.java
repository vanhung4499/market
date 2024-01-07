package com.hnv99.market.domain.user;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
@Embeddable
public class UserInfo {
    @Column(nullable = false)
    private LocalDate birthday;

    @Column(length = 50)
    private String referral;

    @Column(nullable = false, length = 10)
    private String sex;

    public UserInfo() {
    }
}
