package com.dawa.url;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class url {
    //iphone hotspot
 //  public static final String BASE_URL = "http://192.168.43.95:3001/";
    //home wifi
  public static final String BASE_URL = "http://192.168.1.18:3001/";

  //ayadi home wifi
  // public static final String BASE_URL = "http://10.1.10.45:3001/";


    public static String imagePath = BASE_URL ;
    public static String token = "Bearer ";
    public static String status = "Status";
    public Object getContent;

    public static Retrofit getInstance(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return  retrofit;

    }

}
