package com.khmersolution.moduler.configure.util;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.security.crypto.codec.Utf8;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/*
 * Created by cheasocheat
 * On 08, May, 2018
 */
//public class MobilePasswordEncoder implements PasswordEncoder {
public class MobilePasswordEncoder implements PasswordEncoder {

    private static int KEY_LENGTH = 15;
    private static String prefixSalt = "@$#nKr==[";
    private static String suffixSalt = "]==DbmYS_qL#$@";

    @Override
    public String encode(CharSequence rawPassword) {
        String hashed = BCrypt.hashpw(rawPassword.toString(), BCrypt.gensalt(12));
        return hashed;
    }
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return BCrypt.checkpw(rawPassword.toString(), encodedPassword);
    }

   /* @Override
    public String encode(CharSequence rawPassword, String salt) {
        return this.encodePassword(String.valueOf(rawPassword), salt);
    }

    @Override
    public boolean matches(String encodedPassword, CharSequence rawPassword, String salt) {
        return this.isVermatchesified(String.valueOf(rawPassword), salt, encodedPassword);
    }*/


    // rawPass is a raw password supplied by user.
    public static String encodePassword(String rawPass, Object salt) {
        String saltedPass = mergePasswordAndSalt(rawPass, prefixSalt + salt + suffixSalt, false);

        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {

            e.printStackTrace();
        }

        byte[] digest = messageDigest.digest(Utf8.encode(saltedPass));

        int iterations = 1000;
        // "stretch" the encoded value if configured to do so
        for (int i = 1; i < iterations; i++) {
            digest = messageDigest.digest(digest);
        }

        return new String(Hex.encode(digest));
    }

    public boolean isVermatchesified(String rawPass, Object salt, String oldEncodedPasswd) {
        boolean authenticated = false;
        if (rawPass == null || salt == null || oldEncodedPasswd == null) {
            return authenticated;
        }

        String newEncodedPasswd = encodePassword(rawPass, salt);

        return oldEncodedPasswd.equals(newEncodedPasswd);

    }

    public static String mergePasswordAndSalt(String password, Object salt, boolean strict) {
        if (password == null) {
            password = "";
        }

        if (strict && (salt != null)) {
            if ((salt.toString().lastIndexOf("{") != -1) || (salt.toString().lastIndexOf("}") != -1)) {
                throw new IllegalArgumentException("Cannot use { or } in salt.toString()");
            }
        }

        if ((salt == null) || "".equals(salt)) {
            return password;
        } else {
            return password + "{" + salt.toString() + "}";
        }
    }


}
