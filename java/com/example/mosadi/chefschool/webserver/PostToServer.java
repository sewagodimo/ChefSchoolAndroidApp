package com.example.mosadi.chefschool.webserver;

/**
 * Created by Mosadi on 2017/09/26.
 */

import com.example.mosadi.chefschool.LoginActivity;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class PostToServer {
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient();
    String responseString="Nothing happened";
    RequestBody body;

    public   String post(String url, String json) throws IOException {
      //client = new OkHttpClient();
         body = RequestBody.create(JSON, json);

        Request request = new Request.Builder()
                .header("Content-Type", "application/json")
                .url(url)

                .post(body)
                .build();
      responseString= json;

       client.newCall(request).enqueue( new Callback() {
          @Override
          public void onFailure(Call call, IOException e) {
              responseString = "Failed "+call.toString();//body().string();
          }

          @Override
          public void onResponse(Call call, Response response) throws IOException {
              responseString=response.body().toString();;
          }


      });



            return responseString;

        //return "Error in Request";
    }

   public String edit_profile(String name,String surname,String contact, String second_contact) {
        return "{'title' : 'edit_profile', "
                + "'name' : '"+name+" "+surname+"' , "
                + "'contact' : '"+contact+"' , "
                + "'other_contact' : '"+second_contact+"' "
                + "}";
    }
    public String update_address(String id, String country,String province,String city, String surburb) {
        String address=country+"- "+province+"- "+city+"- "+surburb;
        return "{'title':'Change address',"
                + "'student_id':'"+id+" ,"
                + "'address':''" + address + "'"
                + "}";
    }
    public String got_fired(String studentid) {
        return "{'title':'Fired',"
                + "'student_id':'"+studentid+" ,"
                + "'work_status':'unemployed'"
                + "}";
    }
    public String add_student(String name,String contact, String password) {
        return "{'title':'Add Student',"
                + "'name':'"+name+" ,"
                + "'contact':'"+contact+" ,"
                + "'password':'"+password+" '"
                + "'active':'0'"
                + "}";
    }

    public static void main(String[] args) throws IOException {
        PostToServer example = new PostToServer();
        String json = example.got_fired("1");//so number one go fired
        String response = example.post((LoginActivity.URL), json);
        System.out.println(response);
    }
}