package com.example.karthik.mvp.Activity;

import android.database.Observable;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RegisterService {
    String BASE_URL = "http://132.145.45.239/";

    @GET("students")
    Observable<List<Student>> getStudentsRx();
}
