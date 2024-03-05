package com.jcCoder.springboottut.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTProvider {
    public String generateToken(Authentication authentication){
        String username = authentication.getName();
        Date currentDate= new Date();
        Date expiryDate = new Date(currentDate.getTime()+SecurityConstants.JWT_EXPIRATION);

        String token= Jwts.builder()
                .subject(username)
                .issuedAt(currentDate)
                .expiration(expiryDate)
                .signWith(SignatureAlgorithm.HS256, SecurityConstants.JWT_SECRET)
                .compact();

        return  token;

    }

    public String getUserNameFromJwt(String token){
        Claims claim =Jwts.parser()
                .setSigningKey(SecurityConstants.JWT_SECRET)
                .build()
                .parseClaimsJws(token)
                .getPayload();

        return claim.getSubject();
    }

    public boolean validateToken(String token){

           try {
            Jwts.parser().setSigningKey(SecurityConstants.JWT_SECRET).build().parseClaimsJws(token);
            return true;
        }
           catch (Exception e){
               throw new AuthenticationCredentialsNotFoundException("JWT was expired or incorrect");
           }

    }
}
