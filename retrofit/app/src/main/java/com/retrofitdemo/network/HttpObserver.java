package com.retrofitdemo.network;

import android.app.Activity;
import android.net.ParseException;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.JsonParseException;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;
import com.retrofitdemo.R;
import com.retrofitdemo.entity.BaseResponse;
import com.retrofitdemo.utils.DialogUtils;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import org.json.JSONException;

import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;

import static com.retrofitdemo.network.HttpObserver.ExceptionReason.*;

public abstract class HttpObserver<T extends BaseResponse> implements Observer<T> {
    private Activity activity;
    //  Activity 是否在执行onStop()时取消订阅
    private boolean isAddInStop = false;
    private DialogUtils dialogUtils;
    public HttpObserver(Activity activity) {
        this.activity = activity;
        dialogUtils=new DialogUtils();
        dialogUtils.showProgress(activity);
    }

    public HttpObserver(Activity activity, boolean isShowLoading) {
        this.activity = activity;
        dialogUtils=new DialogUtils();
        if (isShowLoading) {
            dialogUtils.showProgress(activity,"Loading...");
        }
    }
    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public void onNext(@NonNull T t) {
        dismissProgress();
        if (Http.SUCCESS.equals(t.getError())) {
            onSuccess(t);
        } else {
            onFail(t);
        }
    }

    private void dismissProgress(){
        if(dialogUtils!=null){
            dialogUtils.dismissDialog();
        }
    }


    @Override
    public void onError(@NonNull Throwable e) {
        LogUtils.e(e.getMessage());
        dismissProgress();
        if (e instanceof HttpException) {     //   HTTP错误
            onException(ExceptionReason.BAD_NETWORK);
        } else if (e instanceof ConnectException
                || e instanceof UnknownHostException) {   //   连接错误
            onException(CONNECT_ERROR);
        } else if (e instanceof InterruptedIOException) {   //  连接超时
            onException(CONNECT_TIMEOUT);
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {   //  解析错误
            onException(PARSE_ERROR);
        } else {
            onException(UNKNOWN_ERROR);
        }
    }

    @Override
    public void onComplete() {

    }

    /**
     * 请求成功
     *
     * @param response 服务器返回的数据
     */
    abstract public void onSuccess(T response);

    /**
     * 服务器返回数据，但响应码不为200
     *
     * @param response 服务器返回的数据
     */
    public void onFail(T response) {
        String message = response.getMessage();
        if (TextUtils.isEmpty(message)) {
            ToastUtils.showShort(R.string.response_return_error);
        } else {
            ToastUtils.showShort(message);
        }
    }

    /**
     * 请求异常
     *
     * @param reason
     */
    public void onException(ExceptionReason reason) {
        switch (reason) {
            case CONNECT_ERROR:
                ToastUtils.showShort(R.string.connect_error);
                break;

            case CONNECT_TIMEOUT:
                ToastUtils.showShort(R.string.connect_timeout);
                break;

            case BAD_NETWORK:
                ToastUtils.showShort(R.string.bad_network);
                break;

            case PARSE_ERROR:
                ToastUtils.showShort(R.string.parse_error);
                break;

            case UNKNOWN_ERROR:
            default:
                ToastUtils.showShort(R.string.unknown_error);
                break;
        }
    }

    /**
     * 请求网络失败原因
     */
    public enum ExceptionReason {
        /**
         * 解析数据失败
         */
        PARSE_ERROR,
        /**
         * 网络问题
         */
        BAD_NETWORK,
        /**
         * 连接错误
         */
        CONNECT_ERROR,
        /**
         * 连接超时
         */
        CONNECT_TIMEOUT,
        /**
         * 未知错误
         */
        UNKNOWN_ERROR,
    }
}
