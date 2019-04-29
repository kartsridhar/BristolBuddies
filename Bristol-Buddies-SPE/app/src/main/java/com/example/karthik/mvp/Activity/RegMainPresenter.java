package com.example.karthik.mvp.Activity;

import io.reactivex.annotations.NonNull;
import rx.Observer;
import rx.Scheduler;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

// FOR MOCKITO TESTS

public class RegMainPresenter implements RegMainContract.Presenter {

    // retrofit description
    @NonNull
    private RegDataSource regDataSource;

    // scheduler on which the API request will operate
    @NonNull
    private Scheduler backgroundScheduler;

    // Scheduler on which we want the observer to wait for
    @NonNull
    private Scheduler mainScheduler;

    @NonNull
    private CompositeSubscription subscriptions;

    private RegMainContract.View view;

    public RegMainPresenter (@NonNull RegDataSource regDataSource,
                             @NonNull Scheduler backgroundScheduler,
                             @NonNull Scheduler mainScheduler,
                             RegMainContract.View view) {
        this.regDataSource = regDataSource;
        this.backgroundScheduler = backgroundScheduler;
        this.mainScheduler = mainScheduler;
        this.view = view;
        subscriptions = new CompositeSubscription();
    }

    @Override
    public void loadData() {
        view.onFetchDataStarted();
        subscriptions.clear();

        Subscription subscription = regDataSource
                .getStudents()
                .subscribeOn(backgroundScheduler)
                .observeOn(mainScheduler)
                .subscribe(new Observer<Student>() {
                    @Override
                    public void onCompleted() {
                        view.onFetchDataCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.onFetchDataError(e);
                    }

                    @Override
                    public void onNext(Student student) {
                        view.onFetchDataSuccess(student);
                    }
                });
        subscriptions.add(subscription);
    }

    @Override
    public void subscribe() {
        loadData();
    }

    @Override
    public void unsubscribe() {
        subscriptions.clear();
    }

    @Override
    public void onDestroy() {
        this.view = null;
    }
}
