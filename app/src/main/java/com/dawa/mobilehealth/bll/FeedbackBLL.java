package com.dawa.mobilehealth.bll;


import com.dawa.api.health_api;
import com.dawa.model.feedbacks;
import com.dawa.url.url;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class FeedbackBLL {



    boolean isSuccess = false;

    public boolean feedback(String email, String message) {
        feedbacks feedback = new feedbacks(email, message);

        health_api healthApi = url.getInstance().create(health_api.class);
        Call<Void> responseCall = healthApi.feed(feedback);

        try {
            Response<Void> loginResponse = responseCall.execute();
            if (loginResponse.isSuccessful()) {
                isSuccess = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }
}
