package com.example.karthik.mvp.Activity;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroClient {

    private static final String BASE_URL = "http://132.145.45.239:8080/";
    private static RetroClient mInstance;
    private Retrofit retrofit;

    private RetroClient() {
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    }

    //Synchronised method to get the singleton instance of the RetroClient
    public static synchronized RetroClient getInstance() {
        if(mInstance == null) {
            mInstance = new RetroClient();
        }
        return mInstance;
    }

    //To get the API
    public RetroAPI getAPI() {
        return retrofit.create(RetroAPI.class);
    }

}
