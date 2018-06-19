package com.qdtckj.base.mvp;

import android.os.Bundle;

import com.qdtckj.base.BaseActionBarActivity;

/**
 * Created by zhangzhenguo on 2018/5/18.
 * 纯粹的 MVP 包装，没有任何View层基础功能
 */

public abstract class BaseMvpActivity<P extends MvpPresenter> extends BaseActionBarActivity implements MvpView {

    protected P presenter;

    protected abstract P createPresenter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = createPresenter();
        if (presenter == null) {
            throw new NullPointerException("Presenter is null! Do you return null in createPresenter()?");
        }
        presenter.onMvpAttachView(this, savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (presenter != null) {
            presenter.onMvpSaveInstanceState(outState);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.onMvpDetachView(false);
        }
    }

}
