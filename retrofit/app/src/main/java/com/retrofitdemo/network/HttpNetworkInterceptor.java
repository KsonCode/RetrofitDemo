package com.retrofitdemo.network;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.NetworkUtils;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class HttpNetworkInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (!NetworkUtils.isConnected()){
            request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
            //无网络
            LogUtils.e("no network");
        }
        Response originalResponse = chain.proceed(request);
        if (NetworkUtils.isConnected()){
            //有网的时候读取接口上的@headers配置，可以在这里进行统一设置
            String cacheContrller = request.cacheControl().toString();
            //@Headers("Cache-Control: public, max-age=3600")
            return originalResponse.newBuilder().header("Cache-Control",cacheContrller)
                    .removeHeader("Pragma").build();
        }else{
            return originalResponse.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=2419200")
                    .removeHeader("Pragma")
                    .build();

        }
    }
}
