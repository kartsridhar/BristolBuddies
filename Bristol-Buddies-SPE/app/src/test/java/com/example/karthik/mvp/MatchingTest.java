package com.example.karthik.mvp;
import com.example.karthik.mvp.Activity.Buddy;
import com.example.karthik.mvp.Activity.Matching;
import com.example.karthik.mvp.Activity.Student;
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

public class MatchingTest {

    private static final String TEST_FNAME = "Matching";
    private static final String TEST_LNAME = "Test";
    private static final String TEST_UNAME = "ab12345";
    private static final String TEST_GENDER = "M";
    private static final String TEST_PWD = "pr0mis1ng";
    private static final String TEST_DEPT = "Engg";
    private static final String TEST_YOS = "2";
    private static final String TEST_NAT = "Indian";
    private static final String TEST_PRE = "01011";
    private static final String TEST_INT = "01110";
    private static final String TEST_PER = "1011";
    private static final String TEST_BUD = "";

    List<Student> mockStudentList;
    List <Buddy> mockBuddyList;
//    MockWebServer mockWebServer;
//    TestSubscriber<List<Student>> mockSubscriber;

    @Before
    public void setUp() {
        Student student = new Student(TEST_FNAME, TEST_LNAME, TEST_GENDER, TEST_UNAME, TEST_PWD,
                TEST_DEPT, TEST_YOS, TEST_NAT, TEST_INT, TEST_PER, TEST_PRE, TEST_BUD);
        mockStudentList = new ArrayList<>();
        mockStudentList.add(student);


//        mockWebServer = new MockWebServer();
//        mockSubscriber = new TestSubscriber<>();
    }

    //exact match buddy

    @Test
    public void testBuddyMatch1(){
        Buddy testbud1 = new Buddy("test","1", "ts17431","Arts","Belgian", "01111","1011","01011","jh1721",0,"","","");
        Buddy testbud2 = new Buddy("test","2", "ts17432","Engineering","Indian", "01111","1011","01011","jh1721",3,"jh16721","kl18090","gh23410");
        Buddy testbud3 = new Buddy("test","3", "ts17433","Social Sciences","Angolan", "10111","0000","00000","jh1721",1,"kh1213","","");

        mockBuddyList = new ArrayList<Buddy>();
        mockBuddyList.add(testbud1);
        mockBuddyList.add(testbud2);
        mockBuddyList.add(testbud3);
        Student teststudent = mockStudentList.get(0);
        assertTrue(Matching.bestMatch(mockBuddyList,teststudent).getUsername().trim().equals("ts17431"));

    }

    //one buddy with all 1s for pers,ints,prefs

    @Test
    public void testBuddyMatch2(){
        Buddy testbud1 = new Buddy("test","1", "ts17431","Arts","Belgian", "00000","0000","00000","jh1721",0,"","","");
        Buddy testbud2 = new Buddy("test","2", "ts17432","Engineering","Indian", "11111","1111","11111","jh1721",0,"","","");
        Buddy testbud3 = new Buddy("test","3", "ts17433","Social Sciences","Angolan", "0000","0000","00000","jh1721",0,"","","");

        mockBuddyList = new ArrayList<Buddy>();
        mockBuddyList.add(testbud1);
        mockBuddyList.add(testbud2);
        mockBuddyList.add(testbud3);
        Student teststudent = mockStudentList.get(0);
        assertTrue(Matching.bestMatch(mockBuddyList,teststudent).getUsername().trim().equals("ts17432"));

    }

    //all 3 buddies taken

    @Test
    public void testBuddyMatch3(){
        Buddy testbud1 = new Buddy("test","1", "ts17431","Arts","Belgian", "00000","1011","00000","jh1721",3,"","","");
        Buddy testbud2 = new Buddy("test","2", "ts17432","Engineering","Indian", "11111","1111","11111","jh1721",3,"","","");
        Buddy testbud3 = new Buddy("test","3", "ts17433","Social Sciences","Angolan", "00111","0000","00000","jh1721",3,"","","");

        mockBuddyList = new ArrayList<Buddy>();
        mockBuddyList.add(testbud1);
        mockBuddyList.add(testbud2);
        mockBuddyList.add(testbud3);
        Student teststudent = mockStudentList.get(0);
        assertTrue(Matching.bestMatch(mockBuddyList,teststudent).getUsername().trim().equals(""));

    }

    //only one buddy left

    @Test
    public void testBuddyMatch4(){
        Buddy testbud1 = new Buddy("test","1", "ts17431","Arts","Belgian", "00000","1011","00000","jh1721",3,"","","");
        Buddy testbud2 = new Buddy("test","2", "ts17432","Engineering","Indian", "11111","1111","11111","jh1721",2,"","","");
        Buddy testbud3 = new Buddy("test","3", "ts17433","Social Sciences","Angolan", "00111","0000","00000","jh1721",3,"","","");

        mockBuddyList = new ArrayList<Buddy>();
        mockBuddyList.add(testbud1);
        mockBuddyList.add(testbud2);
        mockBuddyList.add(testbud3);
        Student teststudent = mockStudentList.get(0);
        assertTrue(Matching.bestMatch(mockBuddyList,teststudent).getUsername().trim().equals("ts17432"));

    }

    //all 0s for ints,prefs,pers

    @Test
    public void testBuddyMatch5(){
        Buddy testbud1 = new Buddy("test","1", "ts17431","Arts","Belgian", "00000","0000","00000","jh1721",0,"","","");
        Buddy testbud2 = new Buddy("test","2", "ts17432","Engineering","Indian", "00000","0000","00000","jh1721",0,"","","");
        Buddy testbud3 = new Buddy("test","3", "ts17433","Social Sciences","Angolan", "00000","0000","00000","jh1721",0,"","","");

        mockBuddyList = new ArrayList<Buddy>();
        mockBuddyList.add(testbud1);
        mockBuddyList.add(testbud2);
        mockBuddyList.add(testbud3);
        Student teststudent = mockStudentList.get(0);
        assertTrue(Matching.bestMatch(mockBuddyList,teststudent).getUsername().trim().equals("ts17432"));

    }


//    @Test
//    public void testServerCall() {
//        //Given
//        String url = "http://132.145.45.239/";
//        mockWebServer.enqueue(new MockResponse().setBody(new Gson().toJson(mockStudentList)));
//        Retrofit retrofit = new Retrofit.Builder()
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
//                .baseUrl(mockWebServer.url(url))
//                .build();
//        RegisterDataSource remoteDataSource = new RegisterDataSource(retrofit);
//
//
//        //When
////        remoteDataSource.getStudentsRx().subscribe(mockSubscriber);
//
//        //Then
//        mockSubscriber.assertNoErrors();
////        mockSubscriber.assertComplete();
//    }




}
