package com.dawa.api;


import com.dawa.model.Booking;
import com.dawa.model.Instructions;
import com.dawa.model.doctors;
import com.dawa.model.feedbacks;
import com.dawa.model.users;
import com.dawa.server_response.ImageResponse;
import com.dawa.server_response.SignUpResponse;



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

public interface health_api {

    @POST("users/signup")
    Call<SignUpResponse> signup(@Body users user);

    @POST ("users/login")
    Call<users> login(@Body users user);

   @POST ("feedbacks/feed")
   Call<Void> feed(@Body feedbacks feedback);

    @Multipart
    @POST("upload")
    Call<ImageResponse> uploadImage(@Part MultipartBody.Part img);

    @GET("users/me")
    Call<users> getUserDetails(@Header("Authorization") String token);

    @PUT("users/me")
    Call<users> updateUser(@Header("Authorization") String token, @Body users users);

//    @PUT("users/me")
//    Call<users> updateHealthRecord(@Header("Authorization") String token, @Body users users);


    @GET("firstaids/firstaiddetails")
    Call<List<Instructions>> firstaidsDetails(@Header("Authorization") String token);

    @GET("firstaids/{codename}")
    Call<List<Instructions>> getCodename(@Header("codename") String codename);


    @GET("doctors/doctordetails")
    Call<List<doctors>> doctorsDetails (@Header("Authorization") String token);


    @GET("doctors/{specialist}")
    Call<List<doctors>> getSpecialist (@Header("specialist") String specialist);


    @FormUrlEncoded
    @POST("bookingdetails/booking")
    Call<Booking> book(@Header("Authorization") String token, @Field("doctors") String doctors, @Field("purpose") String purpose,
                       @Field("date") String date, @Field("time") String time, @Field("hours") String hours);

    @GET("bookingdetails/mybookings")
    Call<List<Booking>> getBooking(@Header("Authorization") String token);
}
