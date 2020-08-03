package com.poll.api;




import com.poll.model.Booking;
import com.poll.model.doctors;
import com.poll.server_response.ImageResponse;

import java.util.List;

import okhttp3.MultipartBody;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface doctor_api {

    @GET("doctors/doctordetails")
    Call<List<doctors>> doctorsDetails(@Header("Authorization") String token);


    @GET("doctors/{specialist}")
    Call<List<doctors>> getSpecialist(@Header("specialist") String specialist);


    @FormUrlEncoded
    @POST("bookingdetails/booking")
    Call<Booking> book(@Header("Authorization") String token, @Field("doctors") String doctors, @Field("purpose") String purpose,
                       @Field("date") String date, @Field("time") String time);

    @GET("bookingdetails/mybookings")
    Call<List<Booking>> getBooking(@Header("Authorization") String token);


    @POST("doctors/postDoctor")
    Call<doctors> add(@Body doctors doctor);

    @Multipart
    @POST ("upload/")
    Call <ImageResponse> DocImgUpload(@Header("Authorization") String token, @Part MultipartBody.Part file);

    @DELETE("doctors/{id}/deleteDoc")
    Call<doctors>deletePost(@Header("Authorization") String token, @Path("id") String id);
}
