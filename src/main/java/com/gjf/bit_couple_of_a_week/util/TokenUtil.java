package com.gjf.bit_couple_of_a_week.util;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenUtil {
    // token有效期
    private static Long tokenValidity = 60 * 60 * 24 * 7L;
    private static String key = "bitcoaw";

    private static Map<String, String> tokenMap = new HashMap<>();
    // 根据学号生成token
    public static String createToken(String schoolId) {
        Algorithm algorithm = Algorithm.HMAC256(TokenUtil.key);

        Date expirationTime = new Date(System.currentTimeMillis() + TokenUtil.tokenValidity);
        // 设置头信息
        Map<String, Object> header = new HashMap<>();
        header.put("type", "JWT");
        header.put("algorithm", "HMAC256");
        // token中有学号信息
        return JWT.create()
                .withHeader(header)
                .withExpiresAt(expirationTime)
                .withClaim("schoolId", schoolId)
                .sign(algorithm);
    }

    // 根据token字符串得到用户信息
    public static Map<String, String> getInfoFromToken(String token) {
        DecodedJWT decode = JWT.decode(token);
        Map<String, String> info = new HashMap<>();
        info.put("schoolId", decode.getClaim("schoolId").asString());
        return info;
    }

    public static void setToken(String token, String message) {
        tokenMap.put(token, message);
    }

    public static String getToken(String token) {
        return tokenMap.get(token);
    }
}
