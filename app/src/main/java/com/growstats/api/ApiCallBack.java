package com.growstats.api;

@FunctionalInterface
public interface ApiCallBack<T> {
    void onResponse(T response);
    default void onFailure(Throwable cause) {}
}
