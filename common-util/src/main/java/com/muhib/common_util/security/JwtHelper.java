package com.muhib.common_util.security;

import com.muhib.common_util.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtHelper {
    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

    //retrieve username from jwt token
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    //retrieve expiration date from jwt token
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    //for retrieving any information from token we will need the secret key
    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().verifyWith(getKey()).build().parseSignedClaims(token).getPayload();
    }

    //check if the token has expired
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    //generate token for user
    public String generateToken(User userModel) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role",userModel.getRole());
        return doGenerateToken(claims, userModel.getEmail());
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {

        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(subject)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .and()
                .signWith(getKey())
                .compact();
    }
    private SecretKey getKey() {
        String secret = "askjdhaskjfhkasjfhkashfkasjfhkasjfhksjfhkasjfhksajfhkasfjhlasfjsafljasfnlasnfals";
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    //validate token
    public Boolean validateToken(String token, User user) {
        final String username = getUsernameFromToken(token);
        return (username.equals(user.getEmail()) && !isTokenExpired(token));
    }
}
