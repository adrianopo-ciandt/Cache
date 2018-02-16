package br.com.app.cache.presenter;

import br.com.app.cache.domain.LoginDomain;
import br.com.app.cache.infrastructure.cache.UserCache;
import br.com.app.cache.model.User;

/**
 * Created by adrianopo on 16/02/18.
 */

public class LoginPresenter {

    private LoginDomain mLoginDomain;

    /**
     * Class constructor
     */
    public LoginPresenter() {
        mLoginDomain = new LoginDomain();
    }

    /**
     * User - Responsible for making calls to the domain
     *
     * @param name - User's Name
     * @param cpf - User's CPF
     * @param pass - User's Password
     * @param token - User's Token
     */
    public void doSave(String name, String cpf, String pass, String token) {
        mLoginDomain.doSave(name, cpf, pass, token);
    }

    /**
     * Clear user
     */
    public void clearUser() {
        User user = UserCache.getInstance().get();
        if (user != null) {
            mLoginDomain.clearUser();
        }
    }
}
