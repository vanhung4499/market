package com.hnv99.market.domain.user.delivery;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@Embeddable
public class Info {
    private static final int MAX_NAME_LENGTH = 30;

    @Column(nullable = false, length = MAX_NAME_LENGTH)
    private String receiver;

    @Column(nullable = false, length = 15)
    private String contact;

    @Enumerated(value = EnumType.STRING)
    private Area receiveArea;

    @Column(length = 10)
    private String entrancePassword;

    @Enumerated(value = EnumType.STRING)
    private AlertTime messageAlertTime;
}
