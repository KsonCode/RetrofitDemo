package com.retrofitdemo.network;

import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.retrofitdemo.api.Api;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class RetrofitUtils {
    private OkHttpClient okHttpClient;
    private volatile static RetrofitUtils mInstance;
    private Retrofit retrofit;
    private RetrofitUtils(){

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
//        Cache cache = new Cache(new File(""),1024);
        okHttpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(new HttpNetworkInterceptor())
                .addInterceptor(httpLoggingInterceptor)
                .readTimeout(8,TimeUnit.SECONDS)
                .writeTimeout(8,TimeUnit.SECONDS)
//                .cache(cache)
                .build();

        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(Api.BASE_URL)
                .client(okHttpClient)
                .build();


    }

    public static RetrofitUtils getmInstance(){
        if (mInstance==null){
            synchronized (RetrofitUtils.class){
                if (mInstance==null){
                    mInstance = new RetrofitUtils();
                }
            }
        }

        return mInstance;
    }

    /**
     * 创建service
     * @param tClass
     * @param <T>
     * @return
     */
    public <T>T create(Class<T> tClass){
        return retrofit.create(tClass);
    }



}
