package com.example.a50249.myapplication;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {
    static public String md5(String p){
        byte[] securityByte = null;
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(p.getBytes());
            securityByte = md.digest();
        }catch (NoSuchAlgorithmException e){
            throw new RuntimeException("No such algorithm!");
        }
        String md5code = new BigInteger(1, securityByte).toString(16);
        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code = "0" + md5code;
        }
        return  md5code;
    }
}
