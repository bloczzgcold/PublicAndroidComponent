package com.qdtckj.component.http.callback;

import com.qdtckj.component.http.entity.Response;

import java.io.IOException;

/**
 * Created by zhangzhenguo on 2018/4/27.
 * callback
 */

public interface Callback {

    void onResponse(Response response);

    void onFailure(IOException e);
}
