package com.example.karthik.mvp.Activity;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RetroAPI {

    @FormUrlEncoded
    @POST("students")
    Call<ResponseBody> createStudent(
        @Field("firstName") String first,
        @Field("lastName") String last,
        @Field("gender") String gender,
        @Field("userName") String user,
        @Field("password") String pwd,
        @Field("department") String dept,
        @Field("yearofStudy") String yos
    );
}
