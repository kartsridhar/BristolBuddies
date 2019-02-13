package com.example.karthik.mvp.Activity;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RetroAPI {

//    @FormUrlEncoded
//    @POST("students")
//    Call<ResponseBody> createStudent(
//        @Field("firstName") String first,
//        @Field("lastName") String last,
//        @Field("gender") String gender,
//        @Field("userName") String user,
//        @Field("password") String pwd,
//        @Field("department") String dept,
//        @Field("yearofStudy") String yos
//    );

    @POST("students")
    Call<Post> createPost(@Body Post post);

    @FormUrlEncoded
    @POST("students")
    Call<Post> createPost(
        @Field("firstName") String first,
        @Field("lastName") String last,
        @Field("gender") String gender,
        @Field("userName") String user,
        @Field("password") String pwd,
        @Field("department") String dept,
        @Field("yearofStudy") String yos
    );

    @PUT("students/firstname/{name}")
    Call<Post> putPost(@Path("fname") String fname, @Body Post post);

}
