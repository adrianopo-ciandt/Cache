package br.com.app.cache.infrastructure.cache;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import br.com.app.cache.infrastructure.Application;
import br.com.app.cache.infrastructure.SecurityBridge;
import br.com.app.cache.utils.AESUtils;

class FileCacheHelper {

    private static final String TAG = FileCacheHelper.class.getSimpleName();

    private FileCacheHelper() {}

    public static String get(String cacheFileName) {
        String cache = null;
        FileReader fileReader = null;
        BufferedReader br = null;

        try {
            Context context = Application.getInstance().getApplicationContext();

            if (!TextUtils.isEmpty(cacheFileName)) {
                String aesKey = SecurityBridge.getAesKey(context);
                String ivKey = SecurityBridge.getIvKey(context);

                File file = new File(context.getFilesDir(), cacheFileName);

                if (file.exists()) {
                    StringBuilder text = new StringBuilder();
                    fileReader = new FileReader(file);
                    br = new BufferedReader(fileReader);
                    String line;

                    while ((line = br.readLine()) != null) {
                        text.append(line);
                        text.append('\n');
                    }

                    cache = AESUtils.decrypt(aesKey, ivKey, text.toString());
                }
            }
        } catch (Exception e) {
           Log.e(TAG, e.toString());
        } finally {
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    Log.e(TAG, e.toString());
                }
            }
            
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    Log.e(TAG, e.toString());
                }
            }
        }

        return cache;
    }

    public static void put(String cacheFileName, String value) {
        try {
            Context context = Application.getInstance().getApplicationContext();
            
            if (!TextUtils.isEmpty(cacheFileName)) {
                String aesKey = SecurityBridge.getAesKey(context);
                String ivKey = SecurityBridge.getIvKey(context);

                String encryptedValue = AESUtils.encrypt(aesKey, ivKey, value);

                FileOutputStream outputStream = context.openFileOutput(cacheFileName, Context.MODE_PRIVATE);
                if (encryptedValue != null) {
                    outputStream.write(encryptedValue.getBytes());
                }
                outputStream.close();
            }
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }
}
