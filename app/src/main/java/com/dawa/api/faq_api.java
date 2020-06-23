package com.dawa.api;


import com.dawa.model.Faqs;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface faq_api {

    @GET("faqs/faqdetails")
    Call<List<Faqs>> faqsDetails(@Header("Authorization") String token);

    @GET("faqs/{question}")
    Call<List<Faqs>> getQuestion(@Header("question") String question);


}
