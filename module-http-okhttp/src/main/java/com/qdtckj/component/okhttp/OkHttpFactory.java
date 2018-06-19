package com.qdtckj.component.okhttp;

import com.qdtckj.component.http.Http;
import com.qdtckj.component.http.utils.HttpFactory;

/**
 * Created by zhangzhenguo on 2018/5/9.
 * okhttp factory
 */

public class OkHttpFactory implements HttpFactory {
    @Override
    public Http create() {
        return new OkHttp(20,20,20);
    }
}
