package com.dawa.api;



import com.dawa.model.Booking;
import com.dawa.model.Faqs;
import com.dawa.model.Instructions;
import com.dawa.model.doctors;
import com.dawa.model.feedbacks;
import com.dawa.model.users;


import java.util.List;


import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface admin_api {

    //user detail

    @GET("users/userdetails")
    Call<List<users>> getCustomer(@Header("Authorization") String token);

    @GET("users/{username}")
    Call<List<users>> getUsername(@Header("username") String question);

    @DELETE("users/deleteUser/{id}")
    Call<users>deleteUserInfo(@Header("Authorization") String token, @Path("id") String id);

    //doctor detail

    @GET("doctors/doctordetails")
    Call<List<doctors>> getDoctor (@Header("Authorization") String token);


    @DELETE("doctors/deleteDoc/{id}")
    Call<doctors>deletePost(@Header("Authorization") String token, @Path("id") String id);


    @GET("doctors/{specialist}")
    Call<List<doctors>> getSpecialist (@Header("specialist") String specialist);

    //Appointment details

    @GET("bookingdetails/mybookings")
    Call<List<Booking>> getAppointment(@Header("Authorization") String token);

    //Admin Feedback details

    @GET("feedbacks/feedbackdetails")
    Call<List<feedbacks>> getUserFeedback(@Header("Authorization") String token);

    @GET("feedbacks/{email}")
    Call<List<feedbacks>> getEmail(@Header("email") String email);

    //Admin Faq details

    @GET("faqs/faqdetails")
    Call<List<Faqs>> getAdminFaqDetails(@Header("Authorization") String token);

    @GET("faqs/{question}")
    Call<List<Faqs>> getQuestion(@Header("question") String question);

    @DELETE("faqs/deleteFaq/{id}")
    Call<Faqs>deleteFaqPost(@Header("Authorization") String token, @Path("id") String id);

    //Admin Firstaid details

    @GET("firstaids/firstaiddetails")
    Call<List<Instructions>> getAdminFirstaidDetails(@Header("Authorization") String token);

    @GET("firstaids/{codename}")
    Call<List<Instructions>> getCodename(@Header("codename") String codename);





}
