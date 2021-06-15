package com.example.gallerypro.Search.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchRetro {

    public static  String MainUrl="https://api.flickr.com/services/rest/";
    public static Retrofit retrofit;

    static Gson gson= new GsonBuilder().setLenient().create();
    public  static Retrofit getRetrofitData()
    {
        if (retrofit==null){
            retrofit=new Retrofit.Builder().baseUrl(MainUrl)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;

    }
}
