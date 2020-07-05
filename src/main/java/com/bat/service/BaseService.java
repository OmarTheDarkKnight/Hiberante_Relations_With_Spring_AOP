package com.bat.service;

import com.bat.dao.CourseDao;
import com.bat.dao.InstructorDao;
import com.bat.dao.ReviewDao;
import com.bat.dao.StudentDao;
import com.bat.dto.BaseDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import javax.crypto.Cipher;

/**
 * AES256 imports
 * import java.security.spec.KeySpec;
 * import java.security.spec.InvalidKeySpecException;
 * import java.io.UnsupportedEncodingException;
 * import javax.crypto.SecretKey;
 * import javax.crypto.SecretKeyFactory;
 * import javax.crypto.spec.IvParameterSpec;
 * import javax.crypto.spec.PBEKeySpec;
 **/

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

    public static void setKey(String myKey)
    {
        MessageDigest sha = null;
        try {
            byte[] key = myKey.getBytes(StandardCharsets.UTF_8);
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public final String encrypt(int intToEnc) {
        try
        {
            setKey(salt);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getUrlEncoder().encodeToString(cipher.doFinal(String.valueOf(intToEnc).getBytes(StandardCharsets.UTF_8)));
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

    /**
     * AES256 encryption decryption methods
     * methods and variables for AES 256
     * private static SecretKeySpec secretKey256;
     *     private static IvParameterSpec ivParameterSpec;
     *
     *     private static void setKey256()
     *     {
     *         try {
     *             String mySecretKey = "TheDarkKnight";
     *             String salt = "Batman";
     *
     *             byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
     *             ivParameterSpec = new IvParameterSpec(iv);
     *
     *             SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
     *             KeySpec spec = new PBEKeySpec(mySecretKey.toCharArray(), salt.getBytes(), 65536, 256);
     *             SecretKey tmp = factory.generateSecret(spec);
     *             secretKey256 = new SecretKeySpec(tmp.getEncoded(), "AES");
     *         }
     *         catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
     *             e.printStackTrace();
     *         }
     *     }
     *
     *     public final String encrypt256(int intToEnc) {
     *         try
     *         {
     *             setKey256();
     *             Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
     *             cipher.init(Cipher.ENCRYPT_MODE, secretKey256, ivParameterSpec);
     *             return Base64.getUrlEncoder().encodeToString(cipher.doFinal(String.valueOf(intToEnc).getBytes(StandardCharsets.UTF_8)));
     *         }
     *         catch (Exception e)
     *         {
     *             System.out.println("Error while encrypting: " + e.toString());
     *         }
     *         return null;
     *     }
     *
     *     public final int decrypt256(String strToDecrypt) {
     *         try
     *         {
     *             setKey256();
     *             Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
     *             cipher.init(Cipher.DECRYPT_MODE, secretKey256, ivParameterSpec);
     *             return Integer.parseInt(new String(cipher.doFinal(Base64.getUrlDecoder().decode(strToDecrypt))));
     *         }
     *         catch (Exception e)
     *         {
     *             System.out.println("Error while decrypting: " + e.toString());
     *         }
     *         return 0;
     *     }
     * */
}
