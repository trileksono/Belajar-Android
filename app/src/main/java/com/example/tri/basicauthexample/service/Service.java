package com.example.tri.basicauthexample.service;

import android.content.Context;

import com.example.tri.basicauthexample.util.Constant;
import com.example.tri.basicauthexample.util.LoggingInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by tri on 5/15/16.
 */
public class Service {

    public API mApi;

    public Service(Context mContext) {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new LoggingInterceptor(mContext));
        Retrofit mRetrofit = new Retrofit
                .Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
        mApi = mRetrofit.create(API.class);
    }

    public API getmApi(){
        return mApi;
    }
}
