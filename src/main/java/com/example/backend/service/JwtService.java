package com.example.backend.service;

import com.example.backend.dto.response.JwtResponse;
import com.example.backend.entity.UserPrincipal;
import com.example.backend.util.Constant;
import com.example.backend.util.MessageUtils;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {

    @Value("${auth.secret.key}")
    private String secretKey;

    @Value("${auth.expire.time}")
    private Long expireTime;

    public JwtResponse createTokenLogin(UserPrincipal userPrincipal) {
        Date currentTime = new Date();
        Date expiresTime = new Date(System.currentTimeMillis() + expireTime);
        String token = Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .setIssuedAt(currentTime)
                .setExpiration(expiresTime)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
        return JwtResponse.builder()
                .username(userPrincipal.getUsername())
                .roles(Constant.gson.toJson(userPrincipal.getAuthorities()))
                .token(token)
                .expiresTime(expiresTime)
                .message(MessageUtils.getMessage("login.success"))
                .build();
    }

    public String getUserNameByToken(String token) {
        String username = Jwts
                .parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        return username;
    }
}
