package com.bawei.carbuydome1.net;

import android.provider.Telephony;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Author:程金柱
 * Date:2019/6/15 8:59
 * Description：
 */

public class HttpUtile {
    private static HttpUtile utile;
    private final Retrofit retrofit;

    private HttpUtile(){

        OkHttpClient okHttpClient=new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

    }
    public static HttpUtile getInstance(){
        if (utile==null){
            synchronized (HttpUtile.class){
                if (utile==null){
                    utile=new HttpUtile();
                }
            }
        }
        return utile;
    }

    public  <T>   T getData(Class<T>  t){
        return retrofit.create(t);
    }
}
