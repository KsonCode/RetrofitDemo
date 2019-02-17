package com.retrofitdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.retrofitdemo.api.Api;
import com.retrofitdemo.api.UserApiService;
import com.retrofitdemo.entity.BaseResponse;
import com.retrofitdemo.entity.UserEntity;
import com.retrofitdemo.network.RetrofitUtils;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

import java.util.List;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        RetrofitUtils.getmInstance().create(UserApiService.class).getGirls(Api.USER_URL)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseResponse<List<UserEntity>>>() {
                    @Override
                    public void accept(BaseResponse<List<UserEntity>> userEntityBaseResponse) throws Exception {

                        List<UserEntity> list = userEntityBaseResponse.results;
                        System.out.println("size:"+list.size());

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }
}
