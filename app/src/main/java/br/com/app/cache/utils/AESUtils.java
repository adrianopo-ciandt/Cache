package br.com.app.cache.utils;

import android.util.Base64;
import android.util.Log;

import java.security.MessageDigest;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESUtils {

    private static final String ENCODING_UTF_8 = "UTF-8";
    private static final String TAG = AESUtils.class.getSimpleName();

    private AESUtils() {}

    public static String encrypt(String aesKey, String ivKey, String value) {
        try {
            byte[] keyBytes = aesKey.getBytes(ENCODING_UTF_8);
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            keyBytes = sha.digest(keyBytes);
            keyBytes = Arrays.copyOf(keyBytes, 16); // use only first 128 bit

            IvParameterSpec iv = new IvParameterSpec(ivKey.getBytes(ENCODING_UTF_8));
            SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, iv);

            byte[] encBytes = cipher.doFinal(value.getBytes(ENCODING_UTF_8));
            return new String(Base64.encode(encBytes, Base64.NO_WRAP));
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }

        return null;
    }

    public static String decrypt(String aesKey, String ivKey, String encryptedValue) {
        try {
            byte[] keyBytes = aesKey.getBytes(ENCODING_UTF_8);
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            keyBytes = sha.digest(keyBytes);
            keyBytes = Arrays.copyOf(keyBytes, 16); // use only first 128 bit

            IvParameterSpec iv = new IvParameterSpec(ivKey.getBytes(ENCODING_UTF_8));
            SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, iv);

            byte[] decBytes = cipher.doFinal(Base64.decode(encryptedValue.getBytes(ENCODING_UTF_8), Base64.NO_WRAP));
            return new String(decBytes);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }

        return null;
    }
}
