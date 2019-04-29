package com.example.karthik.mvp.Activity;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import rx.Observable;
import rx.schedulers.Schedulers;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class RegMainPresenterTest {

    // init 2 mock instances

    @Mock // register data source
    private RegDataSource regDataSource;

    @Mock // view, to receive the fetch
    private RegMainContract.View view;

    @Before // setting up a new mock instance for each test
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test // when data fetch is requested and retrieved successfully
    public void fetchValidDataShouldLoadIntoView() {

        // init empty student to receive in
        Student student = new Student("", "", "",
                "", "", "", "", "", "", "");

        // since its mock, telling the regDataSource to return a fixed student response
        when(regDataSource.getStudents())
                .thenReturn(Observable.just(student));

        // init the presenter using the mocks
        RegMainPresenter regMainPresenter = new RegMainPresenter(
                this.regDataSource,
                Schedulers.immediate(),     // to avoid delay in fetching student data
                Schedulers.immediate(),
                this.view
        );

        // loading the data onto the presenter
        regMainPresenter.loadData();

        // using times to ensure the method is invoked only once
        InOrder inOrder = Mockito.inOrder(view);
        inOrder.verify(view, times(1)).onFetchDataStarted();
        inOrder.verify(view, times(1)).onFetchDataSuccess(student);
        inOrder.verify(view, times(1)).onFetchDataCompleted();
    }

    @Test // when data fetch is requested but data source returns a value error
    public void fetchErrorShouldReturnErrorToView() {
        Exception exception = new Exception();

        when(regDataSource.getStudents())
                .thenReturn(Observable.<Student>error(exception));

        RegMainPresenter regMainPresenter = new RegMainPresenter(
                this.regDataSource,
                Schedulers.immediate(),
                Schedulers.immediate(),
                this.view
        );

        regMainPresenter.loadData();

        InOrder inOrder = Mockito.inOrder(view);
        inOrder.verify(view, times(1)).onFetchDataStarted();
        inOrder.verify(view, times(1)).onFetchDataError(exception);
        verify(view, never()).onFetchDataCompleted();
    }
}
