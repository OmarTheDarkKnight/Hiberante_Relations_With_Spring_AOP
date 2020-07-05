package com.bat.service;

import com.bat.dao.CourseDao;
import com.bat.dao.InstructorDao;
import com.bat.dao.ReviewDao;
import com.bat.dao.StudentDao;
import com.bat.dto.BaseDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public abstract class BaseService {
    @Autowired
    protected CourseDao courseDao;

    @Autowired
    protected ReviewDao reviewDao;

    @Autowired
    protected InstructorDao instructorDao;

    @Autowired
    protected StudentDao studentDao;

    @Autowired
    protected BaseDto baseDto;

    private static SecretKeySpec secretKey;
    private String salt = "Batman";
    private static byte[] key;

    public static void setKey(String myKey)
    {
        MessageDigest sha = null;
        try {
            key = myKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public final String encrypt(int intToEnc) {
        try
        {
            setKey(salt);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getUrlEncoder().encodeToString(cipher.doFinal(String.valueOf(intToEnc).getBytes("UTF-8")));
        }
        catch (Exception e)
        {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }

    public final int decrypt(String strToDecrypt) {
        try
        {
            setKey(salt);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            String integer = new String(cipher.doFinal(Base64.getUrlDecoder().decode(strToDecrypt)));
            return Integer.parseInt(integer);
        }
        catch (Exception e)
        {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return 0;
    }
}
