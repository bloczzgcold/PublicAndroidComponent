package com.qdtckj.component.http;

import com.qdtckj.component.http.utils.HttpFactory;

/**
 * Created by zhangzhenguo on 2018/4/27.
 * HttpClient
 */

public class HttpClient {
    private static final String TAG = "HttpClient";
    private Http http;

    private HttpClient(Builder builder) throws RuntimeException{
        if (builder.httpFactory == null){
            throw new RuntimeException("add a HttpFactory please");
        }
        this.http = builder.httpFactory.create();
    }

    public Http getClient(){
        return this.http;
    }

    public static final class Builder{

        private HttpFactory httpFactory;

        private Builder(){

        }

        public static Builder newBuilder(){
            return new Builder();
        }

        public Builder factory(HttpFactory factory){
            this.httpFactory = factory;
            return this;
        }

        public HttpClient build(){
            return new HttpClient(this);
        }

    }

}
