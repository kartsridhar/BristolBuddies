package com.example.karthik.mvp.Activity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetroAPI {

    @POST("students")
    Call<Student> createStudent(@Body Student student);

    @GET("students")
    Call<List<Student>> getStudents();

    @FormUrlEncoded
    @POST("students")
    Call<Student> userlogin(
            @Field("userName") String username,
            @Field("password") String password
    );


}
