package com.example.gallerypro.Data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetroClass {
    public static  String MainUrl="https://api.flickr.com/services/rest/";
    public static Retrofit  retrofit;

    static Gson gson= new GsonBuilder().setLenient().create();
    public  static Retrofit getRetrofit()
    {
        if (retrofit==null){
            retrofit=new Retrofit.Builder().baseUrl(MainUrl)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;

    }
}
