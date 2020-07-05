package com.dawa.mobilehealth.login;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.dawa.boardcast.BroadCastReceiver;
import com.dawa.createchannel.CreateChannel;

import com.dawa.mobilehealth.admin.AdmindashActivity;
import com.dawa.mobilehealth.MainActivity;
import com.dawa.mobilehealth.R;
import com.dawa.mobilehealth.bll.LoginBLL;
import com.dawa.mobilehealth.welcome_screen.IntroActivity;
import com.dawa.strictmode.StrictModeClass;
import com.dawa.url.url;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnLogin, btnSignup;
    EditText etusername, etpassword;
    public NotificationManagerCompat notificationManagerCompat;
    CheckBox chkRemember;
    SharedPreferences rememberMe;
    Vibrator vibrator;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        setContentView(R.layout.activity_login);
        notificationManagerCompat = NotificationManagerCompat.from(this);
        CreateChannel channel = new CreateChannel(this);
        channel.createChannel();
        rememberMe = getSharedPreferences("User", Context.MODE_PRIVATE);
        btnLogin = findViewById(R.id.btnLogin);
        etusername = findViewById(R.id.username);
        etpassword = findViewById(R.id.password);
        btnSignup = findViewById(R.id.btnRegister);
        chkRemember = findViewById(R.id.cbRemember);
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
//        if (rememberMe.getString("username", "").isEmpty()) {
//            chkRemember.setChecked(false);
//        } else {
//            etusername.setText(rememberMe.getString("username", ""));
//            etpassword.setText(rememberMe.getString("password", ""));
//            chkRemember.setChecked(true);
//        }
        SharedPreferences sharedPreferences = getSharedPreferences("Mobile Health",MODE_PRIVATE);
        String token = sharedPreferences.getString("token","empty");
        String status = sharedPreferences.getString("status","isadmin");
        if(!token.equals("empty") && status.equals("isadmin")){
            url.token = token;
            url.status = status;
            Intent intent = new Intent(LoginActivity.this, AdmindashActivity.class);
            startActivity(intent);
        }
        else if(!token.equals("empty") && !status.equals("isadmin")){
            url.token = token;
            url.status = status;
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }
        btnLogin.setOnClickListener(this);
        btnSignup.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                login();
                break;
            case R.id.btnRegister:
                openSignup();
                break;
            default:
                break;
        }
    }
    private void login() {
        final String username = etusername.getText().toString();
        final String password = etpassword.getText().toString();
        LoginBLL loginBLL = new LoginBLL();
        StrictModeClass.StrictMode();
        if (loginBLL.checkUser(username, password)) {
            SharedPreferences sharedPreferences = getSharedPreferences("Mobile Health", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("token", url.token);
            editor.putString("status", url.status);
            editor.putString("username",  username);
            editor.putString("password", password);
            editor.commit();
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            if (loginBLL.checkadmin(username, password)) {
                SharedPreferences sharedPreferences = getSharedPreferences("Mobile Health", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("token", url.token);
                editor.putString("isadmin", url.status);
                editor.putString("username", username);
                editor.putString("password", password);
                editor.commit();
                Intent intent = new Intent(LoginActivity.this, AdmindashActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Username or Password doesnot match", Toast.LENGTH_SHORT).show();
                vibrator.vibrate(1000);
                etusername.requestFocus();
            }
        }
//        if (!username.isEmpty() && !password.isEmpty()) {
//            users userLogin = new users(username, password);
//            health_api loginApi = url.getInstance().create(health_api.class);
//            Call<users> loginCall = loginApi.login(userLogin);
//
//            loginCall.enqueue(new Callback<users>() {
//                @Override
//                public void onResponse(Call<users> call, Response<users> response) {
//                    System.out.println("The response is : " + response);
//                    if (!response.isSuccessful()) {
//                        Toast.makeText(LoginActivity.this, "Username or Password do not matched", Toast.LENGTH_SHORT).show();
//                        vibrator.vibrate(3000);
//                        return;
//                    } else {
//                        if (chkRemember.isChecked()) {
//                            rememberMe = getSharedPreferences("User", Context.MODE_PRIVATE);
//                            SharedPreferences.Editor editor = rememberMe.edit();
//
//                            editor.putString("username", username);
//                            editor.putString("password", password);
//                            editor.putString("firstName", response.body().getFirstname());
//                            editor.commit();
//                        } else {
//                            rememberMe.edit().clear().commit();
//                        }
//                        url.token += response.body().getToken();
//                        openDashboard();
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<users> call, Throwable t) {
//                    Toast.makeText(LoginActivity.this, "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//
//                }
//            });
//
//        } else {
//            Toast.makeText(LoginActivity.this, "Enter Field", Toast.LENGTH_SHORT).show();
//        }
    }
    public void openDashboard(){
        Intent openDash = new Intent(this, IntroActivity.class);
        startActivity(openDash);
    }
    public void openSignup(){
        Intent openSignup = new Intent(this, SignupActivity.class);
        startActivity(openSignup);
    }
    public void OpenForgotPassword(View view) {
        Intent openForgotPassword = new Intent (this, ForgotPasswordActivity.class);
        startActivity(openForgotPassword);
    }
    private void notifiy() {
        Notification notification = new NotificationCompat.Builder(this, CreateChannel.CHANNEL_1)
                .setSmallIcon(R.drawable.checked)
                .setContentTitle("Mobile Health")
                .setContentText("Login success :" + etusername.getText().toString())
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();
        notificationManagerCompat.notify(1, notification);
    }
    BroadCastReceiver broadCastReceiver= new BroadCastReceiver(this);
    protected void onStart(){
        super.onStart();
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(broadCastReceiver,intentFilter);
    }
    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(broadCastReceiver);
    }
}