package com.example.karthik.mvp.Activity;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class EventController extends Application {
    public static final String TAG = EventController.class.getSimpleName();
    private RequestQueue requestQueue;
    private static EventController mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }
    public static synchronized EventController getmInstance() {
        return mInstance;
    }
    public RequestQueue getRequestQueue() {
        if(requestQueue == null) {
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return requestQueue;
    }
    public <T> void addToRequestQueue(Request<T> request, String tag) {
        request.setTag((TextUtils.isEmpty(tag) ? TAG :tag));
        getRequestQueue().add(request);
    }
    public <T> void addToRequestQueue(Request<T> request) {
        request.setTag(TAG);
        getRequestQueue().add(request);
    }
    public void cancelPendingRequest(Object tag) {
        if (requestQueue != null) {
            requestQueue.cancelAll(tag);
        }
    }
}
