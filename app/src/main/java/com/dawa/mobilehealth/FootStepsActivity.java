package com.dawa.mobilehealth;

import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static android.content.Context.MODE_PRIVATE;


public class FootStepsActivity extends Fragment implements SensorEventListener {

    TextView tv_steps;
    SensorManager sensorManager;
    boolean running = false;

//    SharedPreferences prefs = null;
//    SharedPreferences.Editor editor;
//    int steps;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_foot_steps, container, false);

        tv_steps = view.findViewById(R.id.tv_steps);
        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);

//        prefs = getActivity().getSharedPreferences("MY_PREFS", MODE_PRIVATE);
//        editor = prefs.edit();

        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        running = true;
        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if(countSensor != null){
            sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_UI);
        } else {
            Toast.makeText(getActivity(),"Sensor Not Found", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onPause(){
        super.onPause();
        running = false;
        //sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if(running) {
            tv_steps.setText(String.valueOf(event.values[0]));

//            editor.putInt("steps", steps);
//            editor.commit();
            //  resetStepCount();
           //tv_steps.setText(String.valueOf(prefs.getInt("steps", steps)));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

//    private void resetStepCount() {
//        //   reset every 24 hours.
//        editor.clear();
//        steps = 0;
//        editor.putInt("steps", steps);
//        editor.commit();
//
//    }
}
