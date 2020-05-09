package com.dawa.url;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class url {
    //public static final String BASE_URL = "http://192.168.100.2:3001/";
   // public static final String BASE_URL = "http://http://192.168.137.38:3001/";
   public static final String BASE_URL = "http://10.0.2.2:3001/";
    public static String imagePath = BASE_URL ;
    public static String token = "Bearer ";
    public Object getContent;

    public static Retrofit getInstance(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return  retrofit;

    }

}
