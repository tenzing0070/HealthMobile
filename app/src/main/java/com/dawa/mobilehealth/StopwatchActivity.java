package com.dawa.mobilehealth;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class StopwatchActivity extends Fragment {

    private Button btnstart, btnstop, btnpause;
    ImageView icanchor;
    Animation roundingclock;
    Chronometer timerHere;
    private boolean running;
    private long pauseoffset;

    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_stopwatch, container, false);

        btnstart = view.findViewById(R.id.btnStart);
        btnstop = view.findViewById(R.id.btnStop);
        btnpause = view.findViewById(R.id.btnPause);
        icanchor = view.findViewById(R.id.icanchor);
        timerHere = view.findViewById(R.id.timerHere);

        //create optional animatioin
        btnstop.setAlpha(0);
        btnpause.setAlpha(0);

        //load animation
        roundingclock = AnimationUtils.loadAnimation(getContext(), R.anim.roundclock);

        btnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //passing animation
                icanchor.startAnimation(roundingclock);
                btnstop.animate().alpha(1).translationY(-80).setDuration(300).start();
                btnpause.animate().alpha(1).translationY(-80).setDuration(300).start();
                btnstart.animate().alpha(0).setDuration(300).start();
                //start time
                if (!running) {
                    timerHere.setBase(SystemClock.elapsedRealtime() - pauseoffset);
                    timerHere.start();
                    running = true;
                }
            }
        });

        btnstop.setOnClickListener(new View.OnClickListener() {
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

        btnpause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //pause animation
                icanchor.clearAnimation();
                btnpause.animate().alpha(1).translationY(-80).setDuration(300).start();
                btnstop.animate().alpha(1).translationY(-80).setDuration(300).start();
                btnstart.animate().alpha(1).translationY(-80).setDuration(300).start();
                //pause timer
                if (running) {
                    timerHere.stop();
                    pauseoffset = SystemClock.elapsedRealtime() - timerHere.getBase();
                    running = false;
                }
            }
        });

        return view;

    }
}
