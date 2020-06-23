package com.dawa.url;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class url {
    //iphone hotspot
//    public static final String BASE_URL = "http://172.20.10.8:3001/";
    //home wifi
  public static final String BASE_URL = "http://192.168.1.11:3001/";

   //public static final String BASE_URL = "http://10.0.2.2:3001/";
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
