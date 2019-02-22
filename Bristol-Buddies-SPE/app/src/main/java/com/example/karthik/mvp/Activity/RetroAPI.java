package com.example.karthik.mvp.Activity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetroAPI {

    @POST("students")
    Call<Student> createStudent(@Body Student student);

    @GET("students")
    Call<List<Student>> getStudents();

}
