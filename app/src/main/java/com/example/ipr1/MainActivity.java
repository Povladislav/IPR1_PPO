package com.example.ipr1;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView textView;
    private ImageView imageViewTarget;
    int score;
    SensorManager sensorManager;
    Sensor sensor;
    SensorEventListener sensorEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.textView);
        imageViewTarget = findViewById(R.id.imageViewTarget);
        score = 0;
        createSensors();
    }

    public void createSensors() {
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);

        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float[] rotationMatrix = new float[16];
                SensorManager.getRotationMatrixFromVector(
                        rotationMatrix, event.values);
                float[] remappedRotationMatrix = new float[16];
                SensorManager.remapCoordinateSystem(rotationMatrix,
                        SensorManager.AXIS_Y,
                        SensorManager.AXIS_Z,
                        remappedRotationMatrix);
                float[] orientations = new float[3];
                SensorManager.getOrientation(remappedRotationMatrix, orientations);
                for(int i = 0; i < 3; i++) orientations[i] = (float)(Math.toDegrees(orientations[i]));

                int xPosition = ((int)orientations[1])*20;
                int yPosition = ((int)orientations[2]+90)*10;

                imageView.setX(360+xPosition);
                imageView.setY(yPosition);

                int imageViewX = (int)imageView.getX();
                int imageViewY = (int)imageView.getY();
                int imageViewTargetX = (int)imageViewTarget.getX();
                int imageViewTargetY = (int)imageViewTarget.getY();

                if(imageViewX >= imageViewTargetX-30 && imageViewX <= imageViewTargetX+30 &&
                        imageViewY >= imageViewTargetY+20 && imageViewY <= imageViewTargetY+100) isTarget();
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
    }

    public void isTarget() {
        Toast toast = Toast.makeText(getApplicationContext(),"Great!", Toast.LENGTH_SHORT);
        toast.show();
        if(imageViewTarget.getY()==10) {
            imageViewTarget.setY(925); }
        else {
            imageViewTarget.setY(10);
        }
        score++;
        textView.setText(Integer.toString(score));
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(sensorEventListener,sensor,SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(sensorEventListener);
    }
}