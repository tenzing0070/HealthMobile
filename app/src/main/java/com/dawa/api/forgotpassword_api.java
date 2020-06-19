package com.dawa.api;

import com.dawa.model.Password;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;

public interface forgotpassword_api {


    @FormUrlEncoded
    @POST("forgotpasswords/password")
    Call<Password> submit(@Header("Authorization") String token, @Field("email") String email);


    @GET("forgotpasswords/mypasswords")
    Call<List<Password>> getPassword(@Header("Authorization") String token);


}
