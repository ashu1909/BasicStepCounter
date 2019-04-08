package com.example.basicstepcounter;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {


    SensorManager sensorManager;

    Sensor sensor;
    TextView displaytext;
    boolean running = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        displaytext = (TextView)findViewById(R.id.stepscountlbl);
        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);


    }

    @Override
    protected void onResume() {
        super.onResume();
        running = true;

        sensor= sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

        if(sensor!=null){
            sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_UI);

        }
        else
        {
            Toast.makeText(this, "No Sensor Detected", Toast.LENGTH_LONG).show();
        }
    }







    @Override
    protected void onPause() {
        super.onPause();
        running=false;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if(running){
            displaytext.setText(String.valueOf(event.values[0]));
        }


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
