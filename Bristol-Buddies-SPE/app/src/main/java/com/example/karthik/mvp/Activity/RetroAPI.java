package com.example.karthik.mvp.Activity;

import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RetroAPI {

    @POST("students")
    Call<Student> createStudent(@Body Student student);

    @POST("buddies")
    Call<Buddy> createBuddy(@Body Buddy buddy);

    @GET("students")
    Call<List<Student>> getStudents();

    @GET("buddies")
    Call<List<Buddy>> getBuddies();

    @POST("buddies2")
    Call<Buddy> updateBuddy(@Body Buddy buddy);

    @GET("events")
    Call<List<Event>> getEvents();

    @FormUrlEncoded
    @POST("students")
    Call<Student> userlogin(
            @Field("userName") String username,
            @Field("password") String password
    );


}
