package com.retrofitdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.blankj.utilcode.util.LogUtils;
import com.retrofitdemo.api.Api;
import com.retrofitdemo.api.UserApiService;
import com.retrofitdemo.entity.BaseResponse;
import com.retrofitdemo.entity.UserEntity;
import com.retrofitdemo.network.HttpObserver;
import com.retrofitdemo.network.RetrofitUtils;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DefaultObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

import java.util.List;

public class Main2Activity extends RxAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        RetrofitUtils.getmInstance().create(UserApiService.class).getGirls(Api.USER_URL)
                .compose(bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpObserver<BaseResponse<UserEntity>>(this) {


                    @Override
                    public void onSuccess(BaseResponse<UserEntity> response) {
                        UserEntity list = response.getResults();
                    }
                });
    }
}
