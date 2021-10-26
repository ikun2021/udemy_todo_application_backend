package com.zk.todo.service;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
public class Auth0 {
    private static String SECRET = "zksecret2021";

    public String getToken(String username){
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET); //拿到算法实例，创建签名 secret密钥，只有服务器知道这个密钥
            String token = JWT.create()
                    .withIssuer("auth0")
                    .withIssuedAt(new Date())
                    .withExpiresAt(DateUtils.addHours(new Date(),24))
                    .withClaim("username",username)
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException e) {
            e.printStackTrace();
            return null;
        }
    }


    public String verifyToken(String token) throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).withClaim("username","admin")
                .build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt.toString();
    }

}
