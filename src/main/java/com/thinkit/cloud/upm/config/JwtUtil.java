package com.thinkit.cloud.upm.config;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.zhongkexinli.micro.serv.common.util.StringUtil;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Configuration
public class JwtUtil {
    static final String SECRET = "ThisIsASecret";
    /**
     * token前缀
     */
    @Value("${jtwTokenPrefix}")
    public   String tokenPrefix;
    public static final String HEADER_STRING = "TOKEN";
    
    public  String generateToken(String username,String userId, Long jtwTokenTimeOut) {
        HashMap<String, Object> map = new HashMap<>();
        //you can put any data in the map
        map.put("USER_NAME", username);
        map.put("USER_ID", userId);
        String jwt = Jwts.builder()
                .setClaims(map)
                .setExpiration(new Date(System.currentTimeMillis() + jtwTokenTimeOut*60*24))// 24h
                //.setExpiration(new Date(System.currentTimeMillis() + jtwTokenTimeOut))// 24h
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        return tokenPrefix +jwt; //jwt前面一般都会加Bearer
    }
    

    public  void validateToken(String token) {
        try {
        	Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(tokenPrefix,""))
                    .getBody();
        }catch (Exception e){
            throw new IllegalStateException("Invalid Token. "+e.getMessage());
        }
    }

    public  Map<String, Object> validateTokenAndGetClaims(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        if (StringUtil.isBlank(token)) {
            token = request.getParameter(HEADER_STRING);
        }

        if (token == null)
            throw new TokenValidationException("Missing token");
       return  Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token.replace(tokenPrefix, ""))
                .getBody();
    
    }

    static class TokenValidationException extends RuntimeException {
        public TokenValidationException(String msg) {
            super(msg);
        }
    }
}