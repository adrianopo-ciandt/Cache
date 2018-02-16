package br.com.app.cache.infrastructure;

import android.content.Context;
import android.support.annotation.IntDef;
import android.util.Log;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class SecurityBridge {

    private static final int AES_KEY = 1;
    private static final int IV_KEY = 2;
    private static final String TAG = SecurityBridge.class.getSimpleName();

    public static String getAesKey(Context context) {
        return getKey(context, AES_KEY);
    }

    public static String getIvKey(Context context) {
        return getKey(context, IV_KEY);
    }

    private static String getKey(Context context, @KeyType int key) {
        String keys = getKeys(context);

        if (keys != null && keys.length() > 0) {
            String[] arrayKeys = keys.split(",");

            if (arrayKeys.length >= key) {
                return arrayKeys[key - 1];
            }
        }

        return null;
    }

    static {
        try {
            System.loadLibrary("security");
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }

    private static native String getKeys(Context context);

    @IntDef({
            AES_KEY,
            IV_KEY
    })
    @Retention(RetentionPolicy.SOURCE)
    private @interface KeyType {}
}
