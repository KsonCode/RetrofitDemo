package com.retrofitdemo.network;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * 动态添加头部拦截器
 */
public class HeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();//原始请求对象
        Request nrequest = originalRequest.newBuilder()//创建新的请求对象
//                .addHeader("userId",SPUtils.getInstance().getString("userId"))
//                .addHeader("sessionId",SPUtils.getInstance().getString("sessionId"))
                .addHeader("userId","270")
                .addHeader("sessionId","1550566757244270")
                .build();

        Response response = chain.proceed(nrequest);//最终的响应对象

        return response;
    }
}
