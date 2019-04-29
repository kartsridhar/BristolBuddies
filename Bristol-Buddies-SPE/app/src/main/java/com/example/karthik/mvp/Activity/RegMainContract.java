package com.example.karthik.mvp.Activity;
/*
*
* FOR MOCKITO TESTS
*
* Interface to define the communication between the presenter and the view
*
* Helps in mocking the tests
*
* */
public interface RegMainContract {

    interface View {
        // to notify the view a request is about to start
        void onFetchDataStarted();

        // to notify the view request is done
        void onFetchDataCompleted();

        // to return the requested data
        void onFetchDataSuccess(Student student);

        // to return the error if occurs
        void onFetchDataError(Throwable throwable);
    }

    interface Presenter {
        // to tell the presenter to start fetching the data
        void loadData();

        // to notify the presenter that the View is active
        void subscribe();

        // to notify the presenter that the View is inactive
        void unsubscribe();

        void onDestroy();
    }

}
