package com.hnv99.market.auth;

import com.hnv99.market.common.exception.BaseException;
import com.hnv99.market.domain.user.UserRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import static com.hnv99.market.common.exception.ErrorCode.USER_LOGIN_FAILED;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String loginId) {
        return userRepository.findByLoginId(loginId)
                .map(AuthUser::new)
                .orElseThrow(() -> new BaseException(USER_LOGIN_FAILED));
    }
}
