package com.qdtckj.component.okhttp;

import com.qdtckj.component.http.Http;
import com.qdtckj.component.http.callback.Callback;
import com.qdtckj.component.http.entity.Header;
import com.qdtckj.component.http.entity.Request;
import com.qdtckj.component.http.entity.RequestBody;
import com.qdtckj.component.http.entity.Response;
import com.qdtckj.component.http.utils.HttpUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;

/**
 * Created by zhangzhenguo on 2018/5/4.
 * okhttp
 */

public class OkHttp implements Http {
    private static final int CODE_SUCCESS = 200;

    private final OkHttpClient client;
    private final OkHttpClient.Builder builder;

    public OkHttp() {
        client = new OkHttpClient();
        builder = client.newBuilder();
    }

    public OkHttp(Integer connectTimeout, Integer readTimeout, Integer writeTimeout) {
        this();
        // 设置连接信息
        builder.connectTimeout(connectTimeout, TimeUnit.MILLISECONDS);
        builder.readTimeout(readTimeout, TimeUnit.MILLISECONDS);
        builder.writeTimeout(writeTimeout, TimeUnit.MILLISECONDS);
    }

    @Override
    public Response syncGet(String url, Request request) throws IOException {
        okhttp3.Request req = parseGetReq(url, request);
        return this.execute(req);
    }

    @Override
    public Response syncUrlencoded(String url, Request request) throws IOException {
        okhttp3.Request req = parseUrlencodedReq(url, request);
        return this.execute(req);
    }

    @Override
    public Response syncRaw(String url, Request request) throws IOException {
        okhttp3.Request req = parseJsonReq(url, request);
        return this.execute(req);
    }

    @Override
    public void asynGet(String url, Request request, Callback callback) throws IOException {
        okhttp3.Request req = parseGetReq(url, request);
        this.enqueue(req, callback);
    }

    @Override
    public void asynUrlencoded(String url, Request request, Callback callback) throws IOException {
        okhttp3.Request req = parseUrlencodedReq(url, request);
        this.enqueue(req, callback);
    }

    @Override
    public void asynRaw(String url, Request request, Callback callback) throws IOException {
        okhttp3.Request req = parseJsonReq(url, request);
        this.enqueue(req, callback);
    }

    /**
     * GET请求参数组装
     *
     * @param url     url
     * @param request req
     * @return okhttp req
     */
    private okhttp3.Request parseGetReq(String url, Request request) {
        okhttp3.Request.Builder builder = new okhttp3.Request.Builder();
        List<Header> headers = request.getHeaders();
        String charset = request.getCharset();
        // header
        for (Header header : headers) {
            List<String> values = header.getValues();
            for (String value : values) {
                builder.header(header.getName(), value);
            }
        }

        // param
        List<RequestBody> requestBodyList = request.getRequestBodyList();
        final StringBuilder buffer = new StringBuilder();
        for (RequestBody param : requestBodyList) {
            buffer.append("&").append(param.getName()).append("=").append(HttpUtils.encoded(param.getValue(), charset));
        }

        return builder.url(new StringBuilder(url).append(buffer).toString()).build();
    }

    /**
     * POST请求参数组装
     *
     * @param url     url
     * @param request req
     * @return kohttp req
     */
    private okhttp3.Request parseUrlencodedReq(String url, Request request) {
        okhttp3.Request.Builder builder = new okhttp3.Request.Builder();
        List<Header> headers = request.getHeaders();
        String charset = request.getCharset();
        // header
        for (Header header : headers) {
            List<String> values = header.getValues();
            for (String value : values) {
                builder.header(header.getName(), value);
            }
        }

        // param
        List<RequestBody> requestBodyList = request.getRequestBodyList();
        FormBody.Builder formBuilder = new FormBody.Builder(Charset.forName(charset));
        for (RequestBody param : requestBodyList) {
            formBuilder.add(param.getName(), param.getValue());
        }

        return builder.url(url).post(formBuilder.build()).build();
    }

    /**
     * JSON 内容体请求参数组装
     *
     * @param url     url
     * @param request req
     * @return kohttp req
     */
    private okhttp3.Request parseJsonReq(String url, Request request) {
        okhttp3.Request.Builder builder = new okhttp3.Request.Builder();
        List<Header> headers = request.getHeaders();
        String charset = request.getCharset();
        // header
        for (Header header : headers) {
            List<String> values = header.getValues();
            for (String value : values) {
                builder.header(header.getName(), value);
            }
        }

        String content = request.getContent();
        MediaType JSON = MediaType.parse("application/json; charset=" + charset);
        okhttp3.RequestBody body = okhttp3.RequestBody.create(JSON, content);

        return builder.url(url).post(body).build();
    }

    /**
     * 同步执行HTTP请求
     *
     * @param req 请求
     * @return 响应
     * @throws IOException IO异常
     */
    private Response execute(okhttp3.Request req) throws IOException {
        okhttp3.Response res = client.newCall(req).execute();
        if (res.code() != CODE_SUCCESS) {
            throw new IOException(res.message());
        }
        return this.newResponse(res);
        // end
    }

    /**
     * 异步执行HTTP请求
     *
     * @param req      请求
     * @param callback callback
     */
    private void enqueue(okhttp3.Request req, final Callback callback) throws IOException{
        client.newCall(req).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onFailure(e);
            }

            @Override
            public void onResponse(Call call, okhttp3.Response response) throws IOException {
                if (CODE_SUCCESS != response.code()) {
                    callback.onFailure(new IOException("response code " + response.code()));
                    return;
                }
                Response res = newResponse(response);
                callback.onResponse(res);
            }
        });
    }

    /**
     * 获取响应
     *
     * @param res 响应信息
     * @return 需要的响应
     * @throws IOException 异常
     */
    private Response newResponse(final okhttp3.Response res) throws IOException {
        final String result = res.body().string();
        final List<Header> headerList = new ArrayList<Header>();
        Headers headers = res.headers();
        Set<String> names = headers.names();
        for (String headerName : names) {
            List<String> headerValues = headers.values(headerName);
            Header header = Header.newHeader(headerName, headerValues.toArray(new String[]{}));
            headerList.add(header);
        }
        return new Response() {
            @Override
            public String getResult() {
                return result;
            }

            @Override
            public List<Header> getHeaders() {
                return headerList;
            }
        };
        // end method
    }
}
