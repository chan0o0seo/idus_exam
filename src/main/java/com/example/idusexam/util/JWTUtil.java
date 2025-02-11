package com.example.idusexam.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

public class JWTUtil {

    private static final String SECRET_KEY = "aVn3y56rTgNwLZkQ7mBXdYcKp2JsF4HVlWZ8RqM9TXYHcd3PqLb5NfV6Gz2Km8XY"; // ✅ 64바이트 이상
    private static final long EXPIRATION_TIME = 86400000; // 토큰 만료 시간 (24시간)

    // JWT 토큰 생성
    public static String generateToken(String username) {
        SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(), SignatureAlgorithm.HS512.getJcaName());

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)
                .compact();
    }

    // JWT 토큰에서 사용자 정보 추출 (JwtParser 사용)
    public static Claims extractClaims(String token) {
        SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(), SignatureAlgorithm.HS512.getJcaName());

        JwtParser parser = Jwts.parser()
                .verifyWith(key)
                .build();

        return parser.parseSignedClaims(token).getPayload();
    }

    // JWT 토큰에서 사용자 이름 추출
    public static String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    // 토큰이 만료되었는지 확인
    public static boolean isExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }

    // 토큰 검증
    public static boolean validateToken(String token, String username) {
        return (username.equals(extractUsername(token)) && !isExpired(token));
    }
}
