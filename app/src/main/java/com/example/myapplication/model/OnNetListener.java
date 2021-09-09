package com.example.myapplication.model;

public interface OnNetListener<T> {
    void success(T t);
    void failure(Exception e);
}
