package com.example.karthik.mvp.Activity;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class RegisterTest {

    private static final String TEST_FNAME = "Successful";
    private static final String TEST_LNAME = "Test";
    private static final String TEST_UNAME = "ab12345";
    private static final String TEST_GENDER = "M";
    private static final String TEST_PWD = "pr0mis1ng";
    private static final String TEST_DEPT = "Engg";
    private static final String TEST_YOS = "2";

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
//        assertFalse(Register.checkName("adaefewf@wfwf"));           // no special characters
//        assertFalse(Register.checkName("abc xyz"));               // no spaces

        assertTrue(Register.checkName(TEST_FNAME));                  // correct
        assertTrue(Register.checkName(TEST_LNAME));
    }

//    @Test
//    public void testHttpRequest() throws IOException, InterruptedException {
//        MockWebServer server = new MockWebServer();
//        server.enqueue(new MockResponse().setBody("Found!"));
//        server.start();
//
//        HttpUrl baseURL = server.url("/students");
//        String bodyOfRequest = sendGetRequest(new OkHttpClient(), baseURL);
//        Assert.assertEquals("Found", bodyOfRequest);
//    }
//
//    private String getHttpRequest(OkHttpClient okHttpClient, HttpUrl base) throws IOException {
//        RequestBody body = RequestBody.create(MediaType.parse("text/plain"), "Test");
//
//        okhttp3.Request request = new okhttp3.Request.Builder()
//                .post(body)
//                .url(base)
//                .build();
//        Response response = okHttpClient.newCall(request).execute();
//        return response.body().string();
//    }


}