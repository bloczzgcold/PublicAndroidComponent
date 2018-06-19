package com.qdtckj.component.http.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 请求
 */
public class Request {

    private static final String defaultCharset = "utf-8";
    private String url;
    private String charset = defaultCharset;
    private Map<String, List<Header>> headerMap;
    private List<RequestBody> requestBodyList;
    private String content;

    public Request(Builder builder) {
        this.url = builder.url;
        this.charset = builder.charset;
        this.headerMap = builder.headerMap;
        this.requestBodyList = builder.requestBodyList;
        this.content = builder.content;
    }

    /**
     * 获取url
     *
     * @return string
     */
    public String getUrl() {
        return this.url;
    }

    /**
     * 获取编码集
     *
     * @return string
     */
    public String getCharset() {
        return this.charset;
    }

    /**
     * 获取headers
     *
     * @return map
     */
    public Map<String, List<Header>> getHeaderMap() {
        return this.headerMap;
    }

    public List<Header> getHeaders() {
        if (headerMap == null) {
            return null;
        }
        List<Header> list = new ArrayList<Header>();
        Set<Map.Entry<String, List<Header>>> entries = headerMap.entrySet();
        for (Map.Entry<String, List<Header>> entry : entries) {
            String key = entry.getKey();
            List<Header> value = entry.getValue();
            Header header = Header.newHeader(key, (String[]) value.toArray());
            list.add(header);
        }
        return list;
    }

    /**
     * 获取请求体
     *
     * @return list
     */
    public List<RequestBody> getRequestBodyList() {
        return this.requestBodyList;
    }

    /**
     * 获取请求内容体
     *
     * @return 内容体
     */
    public String getContent() {
        return this.content;
    }

    public static class Builder {
        private String url;
        private String charset;
        private Map<String, List<Header>> headerMap;
        private List<RequestBody> requestBodyList;
        private String content;

        private Builder() {
            headerMap = new HashMap<String, List<Header>>();
            requestBodyList = new ArrayList<RequestBody>();
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        /**
         * 设置编码集
         *
         * @param charset 编码集
         * @return builder
         */
        public Builder setCharset(String charset) {
            this.charset = charset;
            return this;
        }

        /**
         * 设置header
         *
         * @param name  KEY
         * @param value VALUE
         * @return builder
         */
        public Builder header(String name, String value) {
            List<Header> headers = this.headerMap.get(name);
            if (headers == null) {
                headers = new ArrayList<Header>();
                this.headerMap.put(name, headers);
            } else {
                headers.clear();
            }
            headers.add(Header.newHeader(name, value));
            return this;
        }

        /**
         * 添加header
         *
         * @param name  KEY
         * @param value VALUE
         * @return builder
         */
        public Builder addHeader(String name, String value) {
            List<Header> headers = this.headerMap.get(name);
            if (headers == null) {
                headers = new ArrayList<Header>();
                this.headerMap.put(name, headers);
            }
            headers.add(Header.newHeader(name, value));
            return this;
        }

        /**
         * 添加请求体
         *
         * @param name  name
         * @param value value
         * @return builder
         */
        public Builder addRequestBody(String name, String value) {
            requestBodyList.add(RequestBody.newBody(name, value));
            return this;
        }

        /**
         * 设置请求内容
         *
         * @param content 内容体
         * @return builder
         */
        public Builder setContent(String content) {
            this.content = content;
            return this;
        }

        // end Builder class

        public Request build() {
            return new Request(this);
        }
    }
}
