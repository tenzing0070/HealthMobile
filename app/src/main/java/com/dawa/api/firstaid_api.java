package com.dawa.api;


import com.dawa.model.Instructions;


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

public interface firstaid_api {

    @GET("firstaids/firstaiddetails")
    Call<List<Instructions>> firstaidsDetails(@Header("Authorization") String token);

    @GET("firstaids/{codename}")
    Call<List<Instructions>> getCodename(@Header("codename") String codename);


}
