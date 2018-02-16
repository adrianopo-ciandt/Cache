package br.com.app.cache.infrastructure.cache;

import br.com.app.cache.model.User;

public class UserCache extends BaseCache<User> {

    private static final String CACHE_FILE_NAME = "E9DGE5VQ9ZLE0TMT";
    private static UserCache sInstance;

    public static UserCache getInstance() {
        if (sInstance == null) {
            sInstance = new UserCache();
        }

        return sInstance;
    }

    private UserCache() {
        super();
    }

    @Override
    protected String getFileName() {
        return CACHE_FILE_NAME;
    }

    public void clearUser() {
        User user = super.get();
        user.setName(null);
        user.setPass(null);
        user.setCpf(null);
        user.setAccessToken(null);
        put(user);
    }

    public void setUser(String name, String cpf, String pass, String token) {
        User user = get();
        if (user == null) {
            user = new User();
        }
        user.setName(name);
        user.setCpf(cpf);
        user.setPass(pass);
        user.setAccessToken(token);
        put(user);
    }
}
