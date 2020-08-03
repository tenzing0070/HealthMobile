package com.poll.mobilehealthwearable;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;





public class FootStepsActivity extends Activity implements SensorEventListener {


    TextView tv_steps;
    SensorManager sensorManager;
     Sensor mAccelerometer;
    boolean running = false;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foot_steps);

        tv_steps = findViewById(R.id.tv_steps);
    sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);



    }

    @Override
    public void onResume() {
        super.onResume();
        running = true;
        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if(countSensor != null){
            sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_UI);
        } else {
            Toast.makeText(FootStepsActivity.this,"Sensor Not Found", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onPause(){
        super.onPause();
        running = false;

    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if(running) {
            tv_steps.setText(String.valueOf(event.values[0]));


        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void OpenAdminDashboard(View view) {
        Intent openAdminDash = new Intent(this, Dashboard.class);
        startActivity(openAdminDash);
    }


}
