package com.dawa.mobilehealth.bll;

import com.dawa.api.health_api;
import com.dawa.model.users;
import com.dawa.url.url;


import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class LoginBLL {

    boolean isSuccess = true;

    public boolean checkUser(String username, String password) {
        users user = new users(username, password);

        health_api hrsApi = url.getInstance().create(health_api.class);
        Call<users> usersCall = hrsApi.login( user);

        try {
            Response<users> loginResponse = usersCall.execute();
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
