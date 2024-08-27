package com.security.security.jwt;

import com.security.security.domain.mamber.dto.MemberDto;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private SecretKey cachedSecretKey;

    @Value("${jwt.AccessSecret}")
    private String secret;

    @Value("${jwt.AccessExpiration}")
    private Long expiration;

    @Value("${jwt.RefreshExpiration}")
    private Long refreshExpiration;

    private SecretKey getSecretKey() {
        if (cachedSecretKey == null) {
            String keyBase64Encoding = Base64.getEncoder().encodeToString(secret.getBytes());
            cachedSecretKey = Keys.hmacShaKeyFor(keyBase64Encoding.getBytes());
        }
        return cachedSecretKey;
    }

    public String generateAccessToken(MemberDto dto) {
        return Jwts.builder()
                .claim("userId", dto.getUserId())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSecretKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    public String generateRefreshToken(MemberDto dto) {
        return Jwts.builder()
                .claim("userId", dto.getUserId())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + refreshExpiration))
                .signWith(getSecretKey(), SignatureAlgorithm.HS512)
                .compact();
    }
}

