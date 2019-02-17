package com.retrofitdemo.entity;

import java.security.PublicKey;

public class BaseResponse<T> {
    public int code;
    public String message;
    public T results;
    public boolean error;
}
