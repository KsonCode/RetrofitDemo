package com.retrofitdemo.api;

import com.retrofitdemo.entity.BaseResponse;
import com.retrofitdemo.entity.UserEntity;
import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Url;

import java.util.List;

public interface UserApiService {
    @GET
    Observable<BaseResponse<List<UserEntity>>> getGirls(@Url String url);
}
