package com.dawa.mobilehealth;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class StopwatchActivity extends AppCompatActivity {

    private Button btnstart, btnstop, btnpause;
    ImageView icanchor;
    Animation roundingclock;
    Chronometer timerHere;
    private boolean running;
    private long pauseoffset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);

        btnstart = findViewById(R.id.btnStart);
        btnstop = findViewById(R.id.btnStop);
        btnpause = findViewById(R.id.btnPause);
        icanchor = findViewById(R.id.icanchor);
        timerHere = findViewById(R.id.timerHere);

        //create optional animatioin
        btnstop.setAlpha(0);
        btnpause.setAlpha(0);

        //load animation
        roundingclock = AnimationUtils.loadAnimation(this,R.anim.roundclock);

        btnstart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //passing animation
                icanchor.startAnimation(roundingclock);
                btnstop.animate().alpha(1).translationY(-80).setDuration(300).start();
                btnpause.animate().alpha(1).translationY(-80).setDuration(300).start();
                btnstart.animate().alpha(0).setDuration(300).start();
                //start time
                if(!running) {
                    timerHere.setBase(SystemClock.elapsedRealtime()-pauseoffset);
                    timerHere.start();
                    running = true;
                }
            }
        });

        btnstop.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Finish animation
                icanchor.clearAnimation();
                btnstart.animate().alpha(1).translationY(-80).setDuration(300).start();
                btnpause.animate().alpha(0).setDuration(300).start();
                btnstop.animate().alpha(0).setDuration(300).start();
                //stop timer
                timerHere.stop();
               pauseoffset = 0;
               running = false;

            }
        });

        btnpause.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                    //pause animation
                    icanchor.clearAnimation();
                    btnpause.animate().alpha(1).translationY(-80).setDuration(300).start();
                    btnstop.animate().alpha(1).translationY(-80).setDuration(300).start();
                    btnstart.animate().alpha(1).translationY(-80).setDuration(300).start();
                    //pause timer
                if(running) {
                    timerHere.stop();
                    pauseoffset = SystemClock.elapsedRealtime()-timerHere.getBase();
                    running = false;
                }
            }
        });

    }
}
