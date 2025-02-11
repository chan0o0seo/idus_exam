package com.example.idusexam.util;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

public class JWTAuthenticationToken extends AbstractAuthenticationToken {
    private final UserDetails userDetails;  // 인증된 사용자 정보

    public JWTAuthenticationToken(UserDetails userDetails) {
        super(userDetails.getAuthorities()); // 권한 정보 설정
        this.userDetails = userDetails;
        setAuthenticated(true); // 인증된 상태로 설정
    }

    @Override
    public Object getCredentials() {
        return null;  // JWT 기반 인증에서는 비밀번호가 필요 없음
    }

    @Override
    public Object getPrincipal() {
        return userDetails; // 사용자 정보 반환
    }
}
