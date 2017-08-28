package com.iitkgp.amritha.classtest;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager mSensorManager;
    private Sensor mAccelerometer, mLight, mPressure, mTemp;
    TextView tvx, tvy, tvz, pressure, light, gyro;
    Button btnRead;

    private ArrayList<Float> sensorXData, sensorYData, sensorZData, sensorGData;


    private float x;
    private float y;
    private float z;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
            mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            mLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
            mPressure = mSensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
            mTemp = mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);

            mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
            mSensorManager.registerListener(this, mLight, SensorManager.SENSOR_DELAY_NORMAL);
            mSensorManager.registerListener(this, mPressure, SensorManager.SENSOR_DELAY_NORMAL);
            mSensorManager.registerListener(this, mTemp, SensorManager.SENSOR_DELAY_NORMAL);
        }
        //get layout
        // layout = (RelativeLayout) findViewById(R.id.relative);

        //get textviews
        tvx = (TextView) findViewById(R.id.xval);
        tvy = (TextView) findViewById(R.id.yval);
        tvz = (TextView) findViewById(R.id.zval);

        pressure = (TextView) findViewById(R.id.pressure);
        light = (TextView) findViewById(R.id.light);
        gyro = (TextView) findViewById(R.id.gyro);

        light.setText("Illumination");
        light.setText("Pressure");
        light.setText("Temperature");

        sensorXData = new ArrayList<Float>();
        sensorYData = new ArrayList<Float>();
        sensorZData = new ArrayList<Float>();
        sensorGData = new ArrayList<Float>();

        btnRead = (Button) findViewById(R.id.read);
        btnRead.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SecondActivity.class);
                i.putExtra("sensorXData", sensorXData);
                i.putExtra("sensorYData", sensorYData);
                i.putExtra("sensorZData", sensorZData);
                i.putExtra("sensorGData", sensorGData);

                startActivity(i);
            }
        });
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            x = sensorEvent.values[0];
            y = sensorEvent.values[1];
            z = sensorEvent.values[2];

            double root = Math.sqrt(x*x+y*y+z*z);

            sensorXData.add(Double.valueOf(root).floatValue());

            tvx.setText("Accel X = " + String.valueOf(x));
            tvy.setText("Accel Y = " + String.valueOf(y));
            tvz.setText("Aceel Z = " + String.valueOf(z));

        }else if(sensorEvent.sensor.getType() == Sensor.TYPE_LIGHT){
            float illumination = sensorEvent.values[0];
            light.setText("Illumination = " + String.valueOf(illumination));
            sensorYData.add(illumination);
        }else if (sensorEvent.sensor.getType() ==  Sensor.TYPE_PRESSURE){
            float millibars_of_pressure = sensorEvent.values[0];
            pressure.setText("Pressure = " + String.valueOf(millibars_of_pressure));
            sensorZData.add(millibars_of_pressure);

        }else if (sensorEvent.sensor.getType() ==  Sensor.TYPE_AMBIENT_TEMPERATURE){
            float temp = sensorEvent.values[0];
            gyro.setText("Temperature = " + String.valueOf(temp));
            sensorGData.add(temp);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
