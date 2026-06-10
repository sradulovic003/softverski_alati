/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package njt.njt_projekat.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;


import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.Map;
import org.springframework.stereotype.Service;


/**
 *
 * @author Sara
 */
@Service
public class JwtService { //copy paste iz dokumentacije, kreira token

    @Value("${app.jwt.secret}")  //ubacuje vr iz app.prop za tajni kljuc tokena, svi imaju isti
    private String secret;

    @Value("${app.jwt.expiration-ms}")
    private long expirationMs;  //vazenje

    private Key key() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String generate(UserDetails user, Map<String, Object> extra) {
        Date now = new Date();  //pocetak vazenja
        Date exp = new Date(now.getTime() + expirationMs);  //kraj vazenja

        return Jwts.builder()
                .setSubject(user.getUsername())
                .addClaims(extra)
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) {  //Parsira JWT token i iz njega vadi subject, što je username.
        return Jwts.parserBuilder()
                .setSigningKey(key()).build()
                .parseClaimsJws(token).getBody().getSubject();
    }

    public boolean isValid(String token, UserDetails user) {
        try {
            final String un = extractUsername(token);
            return un.equals(user.getUsername())
                    && !isExpired(token);
        } catch (JwtException e) {
            return false;
        }
    }

    private boolean isExpired(String token) {
        Date exp = Jwts.parserBuilder().setSigningKey(key()).build()
                .parseClaimsJws(token).getBody().getExpiration();
        return exp.before(new Date());
    }

}
