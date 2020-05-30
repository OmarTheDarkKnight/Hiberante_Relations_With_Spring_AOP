package com.bat.dto;

public abstract class BaseDto {
    private String baseSalt = ":";

    public String encrypt(String intToEnc, String salt) {
        return salt+baseSalt+intToEnc;
    }

    public int decrypt(String strToDec, String salt) {
        String splitToDec[] = strToDec.trim().split(salt+baseSalt);
        return Integer.parseInt(splitToDec[1]);
    }
}
