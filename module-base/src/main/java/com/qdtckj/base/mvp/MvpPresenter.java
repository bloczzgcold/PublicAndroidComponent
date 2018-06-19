package com.qdtckj.base.mvp;

import android.os.Bundle;

/**
 * Created by zhangzhenguo on 2018/5/18.
 * MVP MvpPresenter
 */

public interface MvpPresenter<V extends MvpView> {

    void onMvpAttachView(V view, Bundle savedInstanceState);

    void onMvpSaveInstanceState(Bundle savedInstanceState);

    void onMvpDetachView(boolean retainInstance);
}
