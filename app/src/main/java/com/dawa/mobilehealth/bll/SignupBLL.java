package com.dawa.mobilehealth.bll;

import com.dawa.api.health_api;
import com.dawa.model.users;
import com.dawa.server_response.SignUpResponse;
import com.dawa.url.url;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class SignupBLL {

    boolean isSuccess = false;

    public boolean signUP(String firstname, String lastname, String address, String age, String phone, String email, String gender, String weight, String height, String bloodgroup, String username, String password, String image) {
        users user = new users(firstname, lastname, address, age, phone, email, gender, weight, height, bloodgroup, username, password, image);

        health_api hrsApi = url.getInstance().create(health_api.class);
        Call<SignUpResponse> responseCall = hrsApi.signup( user);

        try {
            Response<SignUpResponse> loginResponse = responseCall.execute();
            if (loginResponse.isSuccessful()) {

                url.token += loginResponse.body().getToken();
                isSuccess = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }
}
