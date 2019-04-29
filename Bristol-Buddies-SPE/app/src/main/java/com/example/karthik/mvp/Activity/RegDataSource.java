package com.example.karthik.mvp.Activity;

import rx.Observable;

import retrofit2.http.GET;


// FOR MOCKITO TEST

public interface RegDataSource {
    @GET("/students")
    Observable<Student> getStudents();
}
