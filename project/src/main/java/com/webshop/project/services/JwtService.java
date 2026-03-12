package com.webshop.project.services;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private final String SECRET_KEY="x1verysecretkeyz7";

    public String generateToken(String username){
        return Jwts.builder().setSubject(username).setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis()+1000*60*60)).signWith(getSignKey(),SignatureAlgorithm.HS256).compact();
    }

    private Key getSignKey(){
        byte[] bytes=Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(bytes);
    }

    public String extractUsername(String token){
        return Jwts.parserBuilder()
        .setSigningKey(getSignKey()).build()
        .parseClaimsJws(token).getBody().getSubject();
    }
}
