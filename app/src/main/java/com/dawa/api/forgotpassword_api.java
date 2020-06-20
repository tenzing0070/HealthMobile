package com.dawa.api;


import com.dawa.model.Password;

import retrofit2.Call;
import retrofit2.http.Body;

import retrofit2.http.POST;

public interface forgotpassword_api {


    @POST ("passwords/pass")
    Call<Void> pass(@Body Password password);


}
