package br.com.app.cache.domain;

/**
 * Created by adrianopo on 16/02/18.
 */

public class LoginDomain {

    private UserCacheDomain mUserCacheDomain;

    public LoginDomain() {
        mUserCacheDomain = new UserCacheDomain();
    }

    /**
     * User - Responsible for calling API
     *
     * @param name - User's Name
     * @param cpf - User's CPF
     * @param pass - User's Password
     * @param token - User's Token
     */
    public void doSave(String name, String cpf, String pass, String token) {
        mUserCacheDomain.setUser(name, cpf, pass, token);
    }

    /**
     * Clear user
     */
    public void clearUser() {
        mUserCacheDomain.clearUser();
    }
}
