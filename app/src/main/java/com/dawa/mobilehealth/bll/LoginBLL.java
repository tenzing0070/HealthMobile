
package com.dawa.mobilehealth.bll;

        import com.dawa.api.health_api;
        import com.dawa.model.users;
        import com.dawa.server_response.SignUpResponse;
        import com.dawa.url.url;


        import java.io.IOException;

        import retrofit2.Call;
        import retrofit2.Response;

public class LoginBLL {

    boolean isSuccess = false;
    public boolean checkUser(String username, String password) {
        health_api healthApi = url.getInstance().create(health_api.class);
        Call<SignUpResponse> UsersCall = healthApi.checkUser(username, password);
        try {
            Response<SignUpResponse> loginResponse = UsersCall.execute();
            if (loginResponse.isSuccessful() &&
                    loginResponse.body().getStatus().equals("Login Successful")) {
                url.token += loginResponse.body().getToken();
                //url.admin += loginResponse.body().getAdmin();
                // Url.Cookie = imageResponseResponse.headers().get("Set-Cookie");
                isSuccess = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }
    public boolean checkadmin(String username, String password) {
        health_api healthApi = url.getInstance().create(health_api.class);
        Call<SignUpResponse> UsersCall = healthApi.checkUser(username, password);
        try {
            Response<SignUpResponse> loginResponse = UsersCall.execute();
            if (loginResponse.isSuccessful() &&
                    loginResponse.body().getStatus().equals("isadmin")) {
                url.token += loginResponse.body().getToken();
                //url.admin += loginResponse.body().getAdmin();
                // Url.Cookie = imageResponseResponse.headers().get("Set-Cookie");
                isSuccess = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }



}
