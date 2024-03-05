package com.jcCoder.springboottut.security;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class SecurityConstants {
    public static final long JWT_EXPIRATION = 7000000;
    public static final String JWT_SECRET = usingRandomUUID().replaceAll("-","R");

    static String usingRandomUUID() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[36]; // Algorithm used is HS256 - 32 bytes, Here passing 36 bytes secret key slightly more than 32
        random.nextBytes(bytes);
        var encoder = Base64.getUrlEncoder().withoutPadding();
         String secret= encoder.encodeToString(bytes);
         secret.replaceAll("_","d");
         return secret;
    }

}
