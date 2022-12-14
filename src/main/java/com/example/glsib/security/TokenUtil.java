package com.example.glsib.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@Component
public class TokenUtil {
    private final String CLAINS_SUBJECT = "sub";
    private final String CLAIMS_CREATED ="created";

    //@Value("${auth.expiration}")
    private Long TOKEN_VALIDITY = 604800L;

   // @Value("${auth.secret}")
    private String TOKEN_SECRET;

    public String generateToken(UserDetails userDetails) {
        //expration
        //sign
        //compact
        Map<String, Object> claims = new HashMap<> ();
        claims.put(CLAINS_SUBJECT, userDetails.getUsername());
        claims.put(CLAIMS_CREATED, new Date());
        return Jwts.builder()
                .setClaims (claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512,TOKEN_SECRET)
                .compact ();
    }
    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + TOKEN_VALIDITY );
}
}


