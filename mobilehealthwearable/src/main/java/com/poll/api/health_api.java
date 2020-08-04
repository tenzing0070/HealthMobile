package com.poll.api;




import com.poll.model.Booking;
import com.poll.model.users;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;

import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface health_api {



    @POST("users/login")
    Call<users> login(@Body users user);




    @GET("users/me")
    Call<users> getUserDetails(@Header("Authorization") String token);

    @GET("bookingdetails/mybookings")
    Call<List<Booking>> getAppointment(@Header("Authorization") String token);


    @DELETE("bookingdetails/deleteBooking/{id}")
    Call<Booking>deleteBookingPost(@Header("Authorization") String token, @Path("id") String id);


}
