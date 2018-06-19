package com.qdtckj.component.http;

import com.qdtckj.component.http.callback.Callback;
import com.qdtckj.component.http.entity.Request;
import com.qdtckj.component.http.entity.Response;

import java.io.IOException;

/**
 * Created by zhangzhenguo on 2018/4/27.
 * 网络请求
 */

public interface Http {

    /**
     * 同步GET请求
     *
     * @param url     URL
     * @param request request
     * @return 请求结果
     * @throws IOException IO异常
     */
    Response syncGet(String url, Request request) throws IOException;

    /**
     * 表单提交
     *
     * @param url     URL地址
     * @param request 请求
     * @return 执行结果
     * @throws IOException 执行失败
     */
    Response syncUrlencoded(String url, Request request) throws IOException;

    /**
     * 通用同步raw提交
     *
     * @param url     URL
     * @param request request
     * @return 请求结果
     * @throws IOException IO异常
     */
    Response syncRaw(String url, Request request) throws IOException;

    /**
     * 异步GET请求
     *
     * @param url      url
     * @param request  request
     * @param callback 回调
     * @throws IOException IO异常
     */
    void asynGet(String url, Request request, Callback callback) throws IOException;

    /**
     * 异步表单提交
     *
     * @param url      url
     * @param request  request
     * @param callback 回调
     * @throws IOException IO异常
     */
    void asynUrlencoded(String url, Request request, Callback callback) throws IOException;

    /**
     * 异步raw提交
     *
     * @param url      url
     * @param request  request
     * @param callback 回调
     * @throws IOException IO异常
     */
    void asynRaw(String url, Request request, Callback callback) throws IOException;

    public static class Builder {
        protected Integer connectTimeout;
        protected Integer readTimeout;
        protected Integer writeTimeout;
        protected Http http;

        private Builder() {
        }

        public Builder newBuilder() {
            return new Builder();
        }

        /**
         * 设置连接超时时间
         *
         * @param connectTimeout 连接超时时间
         * @return builder
         */
        public Builder connectTimeout(int connectTimeout) {
            this.connectTimeout = connectTimeout;
            return this;
        }

        /**
         * 设置读取数据超时时间
         *
         * @param readTimeout 读取数据超时时间
         * @return builder
         */
        public Builder readTimeout(int readTimeout) {
            this.readTimeout = readTimeout;
            return this;
        }

        /**
         * 设置写入数据超时时间
         *
         * @param writeTimeout 写入数据超时时间
         * @return builder
         */
        public Builder writeTiemout(int writeTimeout) {
            this.writeTimeout = writeTimeout;
            return this;
        }

        /**
         * 设置Client
         * @param http {@link Http}
         * @return builder
         */
        public Builder client(Http http){
            this.http = http;
            return this;
        }

        // end Builder class
    }
}
