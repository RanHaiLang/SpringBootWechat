package com.rminfo.jinmaocs.util;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Date;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: SeaRan
 * Date: 2019/7/29
 * Time: 16:45
 * 项目名：jinmaocs
 * 描述：TODO
 * Description: No Description
 */
public class WXBizMsgCrypt {

    /**
     * 生成Token
     * @return
     */
    public static String makeToken(String session_key,String openid) {
        String token = (session_key + new Random().nextInt(999999999)) + openid;
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            byte md5[] =  md.digest(token.getBytes());
            BASE64Encoder encoder = new BASE64Encoder();
            return encoder.encode(md5);
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }



    public static String sercetKey="mingtianhuigenghao";
    public final static long  keeptime=300000000;

    public static String generToken(String session_key, String openid, String subject){
        long ttlMillis=keeptime;
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(sercetKey);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        JwtBuilder builder = Jwts.builder().setId(session_key)
                .setIssuedAt(now);
        if(subject!=null){
            builder.setSubject(subject);
        }
        if(openid!=null){
            builder.setIssuer(openid);
        }
        builder.signWith(signatureAlgorithm, signingKey);
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }
        return builder.compact();
    }
}
