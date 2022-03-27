package com.forezp.util;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.*;

public class JwtUtils {

    /**
     * 生成 JWT Token
     */
    public static String createJWTToken(String userInfo) throws JWTCreationException {
        String secret = "secret";//假设服务端秘钥
        Algorithm algorithm = Algorithm.HMAC256(secret);
        //jwt 头部信息
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");

        Date nowDate = new Date();
        Date expireDate = AddDate(nowDate, 2 * 60);//120 分钟过期

        String token = JWT.create()
                .withHeader(map)
                .withIssuer("SERVICE") //对应 paylaod iss 节点：签发人
                .withClaim("userInfo", userInfo)
                .withSubject("this is a token demo")//对应 paylaod sub 节点：主题
                .withAudience("Client")//对应 paylaod aud 节点：受众
                .withIssuedAt(nowDate)//对应 paylaod iat 节点：生效时间
                .withExpiresAt(expireDate) //对应 paylaod  exp 签发人 节点：过期时间
                .sign(algorithm);
        return token;
    }

    /**
     * 验证 token
     */
    public static String verifyJWTToken(String token) throws JWTVerificationException {
        Algorithm algorithm = Algorithm.HMAC256("secret");
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("SERVICE")
                .build();

        DecodedJWT jwt = verifier.verify(token);
        String subject = jwt.getSubject();
        Map<String, Claim> claims = jwt.getClaims();
        Claim claim = claims.get("userInfo");
        System.out.println("userInfo：" + claim.asString());

        List<String> audience = jwt.getAudience();
        System.out.println("subject 值：" + subject);
        System.out.println("audience 值：" + audience.get(0));
        return claim.asString();
    }

    /**
     * 时间加减法
     */
    private static Date AddDate(Date date, Integer minute) {
        if (null == date) {
            date = new Date();
        }
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, minute);
        return cal.getTime();

    }


    public static void main(String[] args) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", "aa");
        jsonObject.put("uid", "123");
        jsonObject.put("role", "admin");
        String token = createJWTToken(jsonObject.toJSONString());
        System.out.println(token);
        verifyJWTToken(token);
    }
}
