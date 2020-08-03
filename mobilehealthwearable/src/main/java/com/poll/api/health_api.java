package com.poll.api;




import com.poll.model.users;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface health_api {



    @POST("users/login")
    Call<users> login(@Body users user);




    @GET("users/me")
    Call<users> getUserDetails(@Header("Authorization") String token);



}
