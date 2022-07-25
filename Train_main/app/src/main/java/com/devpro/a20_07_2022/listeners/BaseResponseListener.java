package com.devpro.a20_07_2022.listeners;

public interface BaseResponseListener<T> {
    void didFetch(T response);
    void didError(int code, String message);
}
