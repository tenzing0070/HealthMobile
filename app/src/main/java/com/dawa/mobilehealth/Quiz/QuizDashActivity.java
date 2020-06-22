package com.dawa.mobilehealth.Quiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.dawa.fragment.HomeFragment;
import com.dawa.mobilehealth.FootStepsActivity;
import com.dawa.mobilehealth.MainActivity;
import com.dawa.mobilehealth.R;
import com.dawa.mobilehealth.login.UpdateProfileActivity;


public class QuizDashActivity extends AppCompatActivity {

    ImageView imghome;


    private static final int REQUEST_CODE_QUIZ = 1;

    public  static final String SHARED_PREFS = "sharedPrefs";
    public  static final String KEY_HIGHSCORE = "KeyHighscore";

    private TextView textViewHighscore;

    private int highscore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_dashboard);

        imghome = findViewById(R.id.imghome);

         textViewHighscore =  findViewById(R.id.tex_view_highscore);

        loadHighscore();

        Button buttonStartQuiz = findViewById(R.id.button_start_quiz);
        buttonStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz();
            }
        });

        //get HomeFragment
        imghome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new QuizDashActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new HomeFragment()).commit();
            }
        });


    }
    private void startQuiz() {

        Intent intent = new Intent(QuizDashActivity.this, QuizActivity.class);
        startActivityForResult(intent, REQUEST_CODE_QUIZ);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE_QUIZ){
            if (resultCode == RESULT_OK) {
                int score = data.getIntExtra(QuizActivity.EXTRA_SCORE, 0);
                if (score > highscore){
                    updateHighscore(score);
                }
            }
        }
    }

    private void loadHighscore(){
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        highscore = prefs.getInt(KEY_HIGHSCORE, 0);

         textViewHighscore.setText("Highscore:" + highscore);
    }

    private void updateHighscore(int highscoreNew){
        highscore = highscoreNew;
        textViewHighscore.setText("Highscore:" + highscore);

        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_HIGHSCORE, highscore);
        editor.apply();
    }
}
