package br.com.app.cache.infrastructure;

import java.util.Locale;

public class Application extends android.app.Application {

    private static Application sInstance;

    public static void setsInstance(Application sInstance) {
        Application.sInstance = sInstance;
    }

    public static Application getInstance() {
        if (sInstance == null) {
            throw new IllegalStateException(String.format(Locale.getDefault(),
                    "%s instance is null.",
                    Application.class.getSimpleName()));
        }
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        setsInstance(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}