package com.dawa.api;



import com.dawa.model.users;


import java.util.List;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;


public interface admin_api {



    @GET("users/userdetails")
    Call<List<users>> getCustomer(@Header("Authorization") String token);

    @GET("users/{username}")
    Call<List<users>> getUsername(@Header("username") String question);


}
