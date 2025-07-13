package com.kurung.common.util;

import com.kurung.user.entity.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;

@Component
public class JWTUtil {

    private Key secretKey;
    private final long accessExpiration = 1000L * 60 * 60; // 1시간 (ms)

    // 비밀키 초기화
    @PostConstruct
    public void init() {
        this.secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }

    // 토큰 생성
    public String generateToken(UserEntity user, String category) {
        long now = System.currentTimeMillis();
        return Jwts.builder()
                .setSubject(user.getUserId())
                .claim("nickname", user.getUserNick())
                .claim("role", user.isAdminYN() ? "ADMIN" : "USER")
                .claim("category", category)
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + accessExpiration))
                .signWith(secretKey)
                .compact();
    }

    // Claims 추출
    public Claims getClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // userId 추출
    public String getUserIdFromToken(String token) {
        return getClaimsFromToken(token).getSubject();
    }

    // 만료 확인
    public boolean isTokenExpired(String token) {
        Date expiration = getClaimsFromToken(token).getExpiration();
        return expiration.before(new Date());
    }
}










