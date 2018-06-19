package com.qdtckj.component.http.entity;

/**
 * Created by zhangzhenguo on 2018/4/27.
 */

public class RequestBody {
    private String name;
    private String value;

    private RequestBody() {
    }

    public static RequestBody newBody(String name, String value) {
        RequestBody body = new RequestBody();
        body.name = name;
        body.value = value;
        return body;
    }

    public String getName(){
        return this.name;
    }

    public String getValue(){
        return this.value;
    }
}
