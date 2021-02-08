package com.thinkit.cloud.upm.config;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.thinkit.cloud.upm.bean.JWTInfo;
import com.thinkit.cloud.upm.util.RsaKeyHelper;
import com.thinkit.cloud.upm.util.StringHelper;
import com.zhongkexinli.micro.serv.common.constant.CommonConstants;
import com.zhongkexinli.micro.serv.common.util.StringUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Configuration
public class JwtUtil {
    static final String SECRET = "ThisIsASecret";
    /**
     * token前缀
     */
    @Value("${jtwTokenPrefix:TOKEN_PRE}")
    public   String tokenPrefix;
    public static final String HEADER_STRING = "TOKEN";
    
    private static RsaKeyHelper rsaKeyHelper = new RsaKeyHelper();
    
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
    
    /**
     * 密钥加密token
     *
     * @param jwtInfo
     * @param priKey
     * @param expire
     * @return
     * @throws Exception
     */
    public static String generateToken(IJWTInfo jwtInfo, byte priKey[], int expire) throws Exception {
        String compactJws = Jwts.builder()
                .setSubject(jwtInfo.getUniqueName())
                .claim(CommonConstants.JWT_KEY_USER_ID, jwtInfo.getId())
                .claim(CommonConstants.JWT_KEY_NAME, jwtInfo.getName())
                .setExpiration(DateTime.now().plusSeconds(expire).toDate())
                .signWith(SignatureAlgorithm.RS256, rsaKeyHelper.getPrivateKey(priKey))
                .compact();
        return compactJws;
    }

    /**
     * 公钥解析token
     *
     * @param token
     * @return
     * @throws Exception
     */
    public static Jws<Claims> parserToken(String token, String pubKeyPath) throws Exception {
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(rsaKeyHelper.getPublicKey(pubKeyPath)).parseClaimsJws(token);
        return claimsJws;
    }
    /**
     * 公钥解析token
     *
     * @param token
     * @return
     * @throws Exception
     */
    public static Jws<Claims> parserToken(String token, byte[] pubKey) throws Exception {
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(rsaKeyHelper.getPublicKey(pubKey)).parseClaimsJws(token);
        return claimsJws;
    }
    /**
     * 获取token中的用户信息
     *
     * @param token
     * @param pubKeyPath
     * @return
     * @throws Exception
     */
    public static IJWTInfo getInfoFromToken(String token, String pubKeyPath) throws Exception {
        Jws<Claims> claimsJws = parserToken(token, pubKeyPath);
        Claims body = claimsJws.getBody();
        return new JWTInfo(body.getSubject(), StringHelper.getObjectValue(body.get(CommonConstants.JWT_KEY_USER_ID)), StringHelper.getObjectValue(body.get(CommonConstants.JWT_KEY_NAME)));
    }
    /**
     * 获取token中的用户信息
     *
     * @param token
     * @param pubKey
     * @return
     * @throws Exception
     */
    public static IJWTInfo getInfoFromToken(String token, byte[] pubKey) throws Exception {
        Jws<Claims> claimsJws = parserToken(token, pubKey);
        Claims body = claimsJws.getBody();
        return new JWTInfo(body.getSubject(), StringHelper.getObjectValue(body.get(CommonConstants.JWT_KEY_USER_ID)), StringHelper.getObjectValue(body.get(CommonConstants.JWT_KEY_NAME)));
    }
}