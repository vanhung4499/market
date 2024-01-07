package com.hnv99.market.auth.jwt;

import com.hnv99.market.auth.CustomUserDetailService;
import com.hnv99.market.common.exception.BaseException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.xml.bind.DatatypeConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import static com.hnv99.market.common.exception.ErrorCode.NOT_AUTHORIZED_TOKEN;

@Component
public class JwtProvider {
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 24 * 7; // 7 days
    private static final String AUTHORITY = "authority";

    private final Key key;

    private final CustomUserDetailService userDetailService;

    public JwtProvider(
            @Value("${secret-key}") String secretKey,
            CustomUserDetailService userDetailService
    ) {
        byte[] secretByteKey = DatatypeConverter.parseBase64Binary(secretKey);
        this.key = Keys.hmacShaKeyFor(secretByteKey);
        this.userDetailService = userDetailService;
    }

    public String createToken(Authentication authentication) {
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITY, authorities)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public Authentication getAuthentication(String accessToken) {
        Claims claims = parseClaims(accessToken);
        String username = claims.getSubject();
        Collection<? extends GrantedAuthority> authorities = getAuthority(claims);

        UserDetails userDetails = userDetailService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
    }

    private Collection<? extends GrantedAuthority> getAuthority(Claims claims) {
        try {
            String[] authorities = claims.get(AUTHORITY).toString().split(",");
            return Arrays.stream(authorities)
                    .map(SimpleGrantedAuthority::new)
                    .toList();
        } catch (BaseException e) {
            throw new BaseException(NOT_AUTHORIZED_TOKEN);
        }
    }

    private Claims parseClaims(String accessToken) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
    }
}
