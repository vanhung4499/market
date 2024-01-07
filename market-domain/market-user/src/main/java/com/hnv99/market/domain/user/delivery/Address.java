package com.hnv99.market.domain.user.delivery;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class Address {
    @Column(nullable = false, length = 100)
    private String roadAddress;

    @Column(length = 100)
    private String description;

    @Column(nullable = false)
    private boolean isExpress;

    public String getDescribedAddress() {
        return this.description == null ? this.roadAddress : this.roadAddress + " " + this.description;
    }
}
