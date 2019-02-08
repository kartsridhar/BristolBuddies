package com.example.karthik.mvp.Activity;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class BackgroundTask extends AsyncTask<String, Void, String> {

    AlertDialog ad;
    Context ctx;
    BackgroundTask(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    protected void onPreExecute() {
        ad = new AlertDialog.Builder(ctx).create();
        ad.setTitle("Single Sign-on!");
    }

    @Override
    protected String doInBackground(String... params) {

        String reg_url = "http://localhost/register.php";
        String login_url = "http://localhost/login.php";
        String method = params[0];      //getting the right method
        if(method.equals("register")) {
            String f = params[1];
            String l = params[2];
            String g = params[3];
            String u = params[4];
            String p = params[5];
            String d = params[6];
            String y = params[7];

            try {
                URL url = new URL(reg_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));

                String data = URLEncoder.encode("FIRSTNAME", "UTF-8") + "=" + URLEncoder.encode(f, "UTF-8")
                        + "&" + URLEncoder.encode("LASTNAME", "UTF-8") + "=" + URLEncoder.encode(l, "UTF-8")
                        + "&" + URLEncoder.encode("GENDER", "UTF-8") + "=" + URLEncoder.encode(g, "UTF-8")
                        + "&" + URLEncoder.encode("USERNAME", "UTF-8") + "=" + URLEncoder.encode(u, "UTF-8")
                        + "&" + URLEncoder.encode("PASSWORD", "UTF-8") + "=" + URLEncoder.encode(p, "UTF-8")
                        + "&" + URLEncoder.encode("DEPARTMENT", "UTF-8") + "=" + URLEncoder.encode(d, "UTF-8")
                        + "&" + URLEncoder.encode("YEAROFSTUDY", "UTF-8") + "=" + URLEncoder.encode(y, "UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                InputStream IS = httpURLConnection.getInputStream();
                IS.close();
                httpURLConnection.disconnect();
                return "Registered Successfully!";

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (method.equals("login")) {
            String u = params[1];
            String p = params[2];

            try {
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                String data = URLEncoder.encode("USERNAME", "UTF-8") + "=" + URLEncoder.encode(u, "UTF-8")
                        + "&" + URLEncoder.encode("PASSWORD", "UTF-8") + "=" + URLEncoder.encode(p, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                InputStream IS = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(IS, "iso-8859-1"));
                String response = "";
                String line = "";

                while((line = bufferedReader.readLine()) != null) {
                    response += line;
                }
                bufferedReader.close();
                IS.close();
                httpURLConnection.disconnect();
                return response;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        if(result.equals("Registered Successfully!")) {
            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
        }
        else {
            ad.setMessage(result);
            ad.show();
        }
    }
}
