package com.example.karthik.mvp.Activity;

import com.google.gson.Gson;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.subscribers.TestSubscriber;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class RegisterTest {

    private static final String TEST_FNAME = "Successful";
    private static final String TEST_LNAME = "Test";
    private static final String TEST_UNAME = "ab12345";
    private static final String TEST_PWD = "pr0mis1ng";
    private static final String TEST_DEPT = "Engg";
    private static final String TEST_NAT = "Indian";
    private static final String TEST_PRE = "01011";
    private static final String TEST_INT = "01110";
    private static final String TEST_PER = "1011";
    private static final String TEST_BUD = "Bruce";

    List<Student> mockStudentList;
    MockWebServer mockWebServer;
    TestSubscriber<List<Student>> mockSubscriber;

    @Before
    public void setUp() {
        Student student = new Student(TEST_FNAME, TEST_LNAME, TEST_UNAME, TEST_PWD,
                 TEST_DEPT, TEST_NAT, TEST_INT, TEST_PER, TEST_PRE, TEST_BUD);
        mockStudentList = new ArrayList<>();
        mockStudentList.add(student);

        mockWebServer = new MockWebServer();
        mockSubscriber = new TestSubscriber<>();
    }

    @Test
    public void testServerCall() {
        //Given
        String url = "http://132.145.45.239/";
        mockWebServer.enqueue(new MockResponse().setBody(new Gson().toJson(mockStudentList)));
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(mockWebServer.url(url))
                .build();

        //Then
        mockSubscriber.assertNoErrors();
    }

    @Test
    public void testUsernameValidity() {
        assertFalse(Register.checkUsername(""));                    // test null
        assertFalse(Register.checkUsername("ab1232131"));           // too long
        assertFalse(Register.checkUsername("ab12"));                // too short
        assertFalse(Register.checkUsername("abx7231"));             // invalid format

        assertTrue(Register.checkUsername(TEST_UNAME));              // correct
    }

    @Test
    public void testFullNameValidity() {
        assertFalse(Register.checkName(""));                        // test null
        assertFalse(Register.checkName("2312314"));                 // no numbers

        assertTrue(Register.checkName(TEST_FNAME));                  // correct
        assertTrue(Register.checkName(TEST_LNAME));
    }

}