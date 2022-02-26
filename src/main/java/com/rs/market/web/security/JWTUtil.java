package com.rs.market.web.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


import java.util.Date;


@Component
public class JWTUtil {

    private final String KEY="M4rk3t";

    private final  Algorithm algorithm = Algorithm.HMAC256(KEY);


    private static long DEFAULT_EXPIRE_IN_MINUTS=2;



    public String generateToken(UserDetails userDetails){

        return JWT.create()
                .withSubject(userDetails.getUsername())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(new Date().getTime()+(DEFAULT_EXPIRE_IN_MINUTS*1000)))
                .sign(algorithm);
    }

    public boolean validateToken (String token, UserDetails userDetails){

        return userDetails.getUsername().equals(extractUsername(token).replace("\"", "")) && notIsTokenExpired(token);

    }

    public String extractUsername(String token){
        return getClaim(token, "sub");
    }

    public boolean notIsTokenExpired(String token){
        try {
            JWTVerifier verifier = JWT.require(algorithm)
                    .acceptExpiresAt(DEFAULT_EXPIRE_IN_MINUTS)
                    .build();
            verifier.verify(token);
            return true;

        }catch (JWTVerificationException e){
            return false;
        }
    }

    //public Predicate<String> dateexpired = (number)-> new Date().getTime() >= Integer.parseInt(number);

    private String getClaim(String token, String claimKey){
        DecodedJWT decodedJWT = JWT.decode(token);
        return decodedJWT.getClaims().get(claimKey).toString();

    }
}
