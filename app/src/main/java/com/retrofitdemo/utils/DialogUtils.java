package com.retrofitdemo.utils;

import android.content.Context;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class DialogUtils {

    private static SweetAlertDialog sweetAlertDialog = null;
    /**
     * 显示进度条
     * @param context
     * @return
     */
    public static SweetAlertDialog showProgress(Context context){
        try {
            if(context!=null){
                if(sweetAlertDialog==null){
                    synchronized (SweetAlertDialog.class) {
                        sweetAlertDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
                    }
                }
                sweetAlertDialog.setTitleText("请稍后...");
                sweetAlertDialog.setCancelable(true);
                sweetAlertDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog mSweetAlertDialog) {
                        mSweetAlertDialog.dismiss();
                        sweetAlertDialog = null;
                    }
                });
                sweetAlertDialog.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sweetAlertDialog;
    }

    /**
     * 成功提示
     * @param context
     * @param msg
     * @param listener
     * @return
     */
    public static SweetAlertDialog showSucceed(Context context, String msg, SweetAlertDialog.OnSweetClickListener listener){
        try {
            if(context!=null){
                if(sweetAlertDialog==null){
                    synchronized (SweetAlertDialog.class) {
                        sweetAlertDialog = new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE);
                    }
                }
                sweetAlertDialog.setTitleText("提示");
                sweetAlertDialog.setContentText(msg);
                sweetAlertDialog.setCancelText("取消");
                sweetAlertDialog.setConfirmText("确认");
                sweetAlertDialog.setConfirmClickListener(listener);
                sweetAlertDialog.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sweetAlertDialog;
    }

    /**
     * 警告提示
     * @param context
     * @param msg
     * @param listener
     * @return
     */
    public static SweetAlertDialog showWarning(Context context, String msg, SweetAlertDialog.OnSweetClickListener listener){
        try {
            if(context!=null){
                if(sweetAlertDialog==null){
                    synchronized (SweetAlertDialog.class) {
                        sweetAlertDialog = new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE);
                    }
                }
                sweetAlertDialog.setTitleText("提示");
                sweetAlertDialog.setContentText(msg);
                sweetAlertDialog.setCancelText("取消");
                sweetAlertDialog.setConfirmText("确认");
                sweetAlertDialog.setConfirmClickListener(listener);
                sweetAlertDialog.show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return sweetAlertDialog;
    }

    public static SweetAlertDialog showError(Context context, String msg, SweetAlertDialog.OnSweetClickListener listener){

        try {
            if(context!=null){
                if(sweetAlertDialog==null){
                    synchronized (SweetAlertDialog.class) {
                        sweetAlertDialog = new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE);
                    }
                }
                sweetAlertDialog.setTitleText("提示");
                sweetAlertDialog.setContentText(msg);
                sweetAlertDialog.setCancelText("取消");
                sweetAlertDialog.setConfirmText("确认");
                sweetAlertDialog.setConfirmClickListener(listener);
                sweetAlertDialog.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sweetAlertDialog;
    }
    /**
     *
     * @param context
     * @return
     */
    public static SweetAlertDialog showProgress(Context context, String msg){
        try {
            if(context!=null){
                if(sweetAlertDialog==null){
                    synchronized (SweetAlertDialog.class) {
                        sweetAlertDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
                    }
                }
                sweetAlertDialog.setTitleText(msg);
                sweetAlertDialog.setCancelable(true);
                sweetAlertDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog mSweetAlertDialog) {
                        mSweetAlertDialog.dismiss();
                        sweetAlertDialog = null;
                    }
                });
                sweetAlertDialog.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sweetAlertDialog;
    }

    public static void dismissDialog(){
        if(sweetAlertDialog!=null){
            try {
                sweetAlertDialog.dismiss();
                sweetAlertDialog = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *
     * @param context
     * @return
     */
    public static SweetAlertDialog showSucceed(Context context, String msg){
        try {
            if (context != null) {
                if(sweetAlertDialog==null){
                    synchronized (SweetAlertDialog.class) {
                        sweetAlertDialog = new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE);
                    }
                }
                sweetAlertDialog.setContentText(msg);
                sweetAlertDialog.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sweetAlertDialog;
    }

}
