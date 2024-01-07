package com.hnv99.market.auth;

import com.hnv99.market.auth.jwt.JwtProvider;
import com.hnv99.market.common.exception.BaseException;
import com.hnv99.market.domain.user.*;
import com.hnv99.market.domain.user.delivery.Delivery;
import com.hnv99.market.domain.user.delivery.DeliveryRepository;
import com.hnv99.market.web.user.Login;
import com.hnv99.market.web.user.Register;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.hnv99.market.common.exception.ErrorCode.ID_EXISTED;

@AllArgsConstructor
@Service
@Transactional(readOnly = true)
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final DeliveryRepository deliveryRepository;

    public Login.Response login(String loginId, String password) {
        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(loginId, password);
        Authentication authorized = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        String token = jwtProvider.createToken(authorized);
        return new Login.Response(token);
    }

    @Transactional
    public void register(Register.Request request) {
        User newUser = toUser(request);

        if (checkId(request.loginId())) {
            throw new BaseException(ID_EXISTED);
        }

        if (checkEmail(request.email())) {
            throw new BaseException(ID_EXISTED);
        }

        User savedUser = userRepository.save(newUser);

        Delivery delivery = Delivery.builder()
                .user(savedUser)
                .build();


        userRepository.save(newUser);

    }


    private User toUser(Register.Request request) {
        return User.builder()
                .loginId(request.loginId())
                .password(encodePassword(request.password()))
                .name(request.name())
                .email(request.email())
                .phoneNumber(request.phoneNumber())
                .birthday(request.birthday())
                .sex(request.sex())
                .referral(request.referral())
                .tier(Tier.NORMAL)
                .role(Role.ROLE_USER)
                .build();
    }

    public String encodePassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    public Boolean checkId(String id) {
        return userRepository.existsByLoginId(id);
    }

    public Boolean checkEmail(String email) {
        return userRepository.existsByEmail(email);
    }

}
