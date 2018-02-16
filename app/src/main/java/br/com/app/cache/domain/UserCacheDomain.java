package br.com.app.cache.domain;

import br.com.app.cache.infrastructure.cache.UserCache;

public class UserCacheDomain {

    /**
     * Clears user
     */
    public void clearUser() {
        UserCache.getInstance().clearUser();
    }

    /**
     * Set user
     */
    public void setUser(String name, String cpf, String pass, String token) {
        UserCache.getInstance().setUser(name, cpf, pass, token);
    }
}