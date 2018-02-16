package br.com.app.cache.infrastructure.cache;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import br.com.app.cache.infrastructure.Application;

abstract class BaseCache<T> {

    private final Gson mGson;
    private String mJsonSession;

    BaseCache() {
        super();
        mGson = new Gson();
        mJsonSession = null;
    }

    protected abstract String getFileName();

    public T get() {
        try {
            if ((mJsonSession == null) || (TextUtils.isEmpty(mJsonSession.trim()))) {
                mJsonSession = FileCacheHelper.get(getFileName());
            }
            return mGson.fromJson(mJsonSession, getCacheType());
        } catch (Exception e) {
            insertLog(e.getMessage(), e);
            return null;
        }
    }

    public void put(T value) {
        try {
            String json = mGson.toJson(value);
            mJsonSession = json;
            FileCacheHelper.put(getFileName(), json);
        } catch (Exception e) {
            insertLog(e.getMessage(), e);
        }
    }

    public void clear() {
        mJsonSession = null;
        File cacheFile = new File(Application.getInstance().getFilesDir(),
                getFileName());
        if (cacheFile.exists() && !cacheFile.delete()) {
            insertLog("Failed to clear cache file.");
        }
    }

    @SuppressWarnings("unchecked")
    private Type getCacheType() {
        return TypeToken.get(((ParameterizedType) (getClass()
                .getGenericSuperclass()))
                .getActualTypeArguments()[0]).getType();
    }

    protected void insertLog(String message) {
        Log.d("BaseCache", message);
    }

    protected void insertLog(String message, Throwable throwable) {
        Log.e(throwable.toString(), message);
    }
}
