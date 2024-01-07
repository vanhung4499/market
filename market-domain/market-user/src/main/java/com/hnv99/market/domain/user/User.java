package com.hnv99.market.domain.user;

import com.hnv99.market.data.BaseEntity;
import com.hnv99.market.domain.user.stategy.Reward;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Getter
@Entity
@SuperBuilder
@Table(name = "users")
public class User extends BaseEntity {

    @Column(nullable = false, length = 10)
    private String name;

    @Column(nullable = false, length = 50)
    private String loginId;

    @Column(nullable = false)
    private String password;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private Tier tier;

    @Column(nullable = false, length = 50)
    private String email;

    @Column(nullable = false)
    private LocalDate birthday;

    @Column(length = 50)
    private String referral;

    @Column(nullable = false, length = 10)
    private String sex;

    private String payPassword;

    @Column(nullable = false, length = 15)
    private String phoneNumber;

    @Column(nullable = false)
    private Integer reward;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Role role;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Status status;

    public User() {
    }

    public void update(String name, String password, String sex, LocalDate birth, String phoneNumber) {
        this.name = name;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.birthday = birth;
        this.sex = sex;
    }

    public void updatePayPassword(String payPassword) {
        this.payPassword = payPassword;
    }

    public int saveReward(Reward reward, int totalPrice) {
        int totalReward = reward.saveReward(totalPrice);

        return this.reward += totalReward;
    }
}
