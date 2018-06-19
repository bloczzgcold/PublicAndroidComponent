package com.qdtckj.component.http.entity;

import java.util.Arrays;
import java.util.List;

/**
 * Header信息
 */
public class Header {

    private String name;
    private List<String> values;

    private Header() {
    }

    public static Header newHeader(String name, String... values) {
        Header header = new Header();
        header.name = name;
        header.values = Arrays.asList(values);
        return header;
    }

    public List<String> getValues(){
        return this.values;
    }

    public String getName(){
        return this.name;
    }

}
