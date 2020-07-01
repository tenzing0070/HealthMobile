package com.dawa.api;



import com.dawa.model.Booking;
import com.dawa.model.doctors;
import com.dawa.model.users;


import java.util.List;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;


public interface admin_api {

    //user detail

    @GET("users/userdetails")
    Call<List<users>> getCustomer(@Header("Authorization") String token);

    @GET("users/{username}")
    Call<List<users>> getUsername(@Header("username") String question);

    //doctor detail

    @GET("doctors/doctordetails")
    Call<List<doctors>> getDoctor (@Header("Authorization") String token);


    @GET("doctors/{specialist}")
    Call<List<doctors>> getSpecialist (@Header("specialist") String specialist);

    //Appointment details

    @GET("bookingdetails/mybookings")
    Call<List<Booking>> getAppointment(@Header("Authorization") String token);


}
