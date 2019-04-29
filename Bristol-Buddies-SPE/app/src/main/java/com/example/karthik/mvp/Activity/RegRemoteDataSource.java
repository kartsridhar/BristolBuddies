package com.example.karthik.mvp.Activity;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/*
* FOR MOCKITO TESTS
*
* Interface to instantiate Retrofit with RxJava Adapter.
*
* This should perform an HTTP request and convert the response into an Observable
*
* */
public class RegRemoteDataSource implements RegDataSource {

    private RegDataSource api;
    private final String URL = "http://132.145.45.239/";

    public RegRemoteDataSource() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        this.api = retrofit.create(RegDataSource.class);
    }

    @Override
    public Observable<Student> getStudents() {
        return this.api.getStudents();
    }
}
