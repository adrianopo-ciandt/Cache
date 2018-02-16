package br.com.app.cache.view.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.app.cache.R;
import br.com.app.cache.infrastructure.cache.UserCache;
import br.com.app.cache.model.User;
import br.com.app.cache.presenter.LoginPresenter;
import br.com.app.cache.view.ui.base.BaseActivity;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by adrianopo on 16/02/18.
 */

public class LoginActivity extends BaseActivity {

    //region [ ButterKnife ]
    @BindView(R.id.etName) EditText etName;
    @BindView(R.id.etCPF) EditText etCPF;
    @BindView(R.id.etPass) EditText etPass;
    @BindView(R.id.etToken) EditText etToken;
    @BindView(R.id.btnSave) Button btnSave;
    //endregion


    private LoginPresenter mLoginPresenter;
    private User user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init() {
        mLoginPresenter = new LoginPresenter();
        setLayout();
    }

    @OnClick(R.id.btnSave)
    public void onBtnLoginClick() {
        doSave();
    }

    private void doSave (){
        mLoginPresenter.doSave(etName.getText().toString(), etCPF.getText().toString(),
                etPass.getText().toString(), etToken.getText().toString());
        Snackbar snackbar = Snackbar
                .make(findViewById(android.R.id.content), "Salvo no cache", Snackbar.LENGTH_LONG)
                .setAction("Limpar cache", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mLoginPresenter.clearUser();
                        setLayout();
                    }
                });
        snackbar.show();
    }

    private void setLayout() {
        user = UserCache.getInstance().get();
        if (user != null) {
            etName.setText(user.getName());
            etCPF.setText(user.getCpf());
            etPass.setText(user.getPass());
            etToken.setText(user.getAccessToken());
        } else {
            etName.setText(null);
            etCPF.setText(null);
            etPass.setText(null);
            etToken.setText(null);
        }
    }
}
