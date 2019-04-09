package com.example.karthik.mvp.Activity;

import android.database.Observable;

import java.util.List;
import retrofit2.Retrofit;

public class RegisterDataSource implements RegisterService {

    private RegisterService api;

    public RegisterDataSource(Retrofit retrofit) {
        this.api = retrofit.create(RegisterService.class);
    }

    @Override
    public Observable<List<Student>> getStudentsRx() {
        return api.getStudentsRx();
    }
}
