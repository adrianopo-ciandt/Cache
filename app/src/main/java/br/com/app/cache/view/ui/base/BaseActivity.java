package br.com.app.cache.view.ui.base;

import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        initButterKnife();
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        initButterKnife();
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        initButterKnife();
    }

    /**
     * Realiza o bind da activity atual no ButterKnife
     */
    private void initButterKnife() {
        ButterKnife.bind(this);
    }
}
