package com.example.feritgrken.retrofitrest.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public  static final String API_BAS_URL="http://jsonplaceholder.typicode.com/";
    private  static Retrofit.Builder getRetroitInstance()
    {
        return  new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(API_BAS_URL);

    }

    public static ApiService getService()
    {
        return  getRetroitInstance().build().create(ApiService.class);
    }
}
