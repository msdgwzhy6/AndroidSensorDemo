package com.fedming.androidsensordemo;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Android sensor demo
 * Created by Bruce on 2015/6/5.
 */

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private TextView directTextView;
    private TextView gyroTextView;
    private TextView magneticTextView;
    private TextView gravityTextView;
    private TextView linearTextView;
    private TextView lightTextView;

    private SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        directTextView = (TextView) findViewById(R.id.direct);
        gravityTextView = (TextView) findViewById(R.id.gravity);
        gyroTextView = (TextView) findViewById(R.id.gyro);
        magneticTextView = (TextView) findViewById(R.id.magnetic);
        lightTextView = (TextView) findViewById(R.id.light);
        linearTextView = (TextView) findViewById(R.id.linear);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        //注册监听器
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                SensorManager.SENSOR_DELAY_GAME);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE),
                SensorManager.SENSOR_DELAY_GAME);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),
                SensorManager.SENSOR_DELAY_GAME);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY),
                SensorManager.SENSOR_DELAY_GAME);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION),
                SensorManager.SENSOR_DELAY_GAME);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT),
                SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onPause() {
        //取消注册
        sensorManager.unregisterListener(this);
        super.onPause();
    }

    @Override
    protected void onStop() {
        //取消注册
        sensorManager.unregisterListener(this);
        super.onStop();
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        float values[] = sensorEvent.values;
        int sensorType = sensorEvent.sensor.getType();
        //传感器返回值
        StringBuilder sensorValues;

        switch (sensorType) {

            case Sensor.TYPE_ORIENTATION:
                //方向传感器
                sensorValues = new StringBuilder();
                sensorValues.append("绕Z轴转过的角度：");
                sensorValues.append(values[0]);
                sensorValues.append("°\n绕X轴转过的角度：");
                sensorValues.append(values[1]);
                sensorValues.append("°\n绕Y轴转过的角度：");
                sensorValues.append(values[2]);
                directTextView.setText(sensorValues.toString());
                break;
            case Sensor.TYPE_GYROSCOPE:
                //陀螺仪传感器
                sensorValues = new StringBuilder();
                sensorValues.append("绕X轴旋转的角速度：");
                sensorValues.append(values[0]);
                sensorValues.append("\n绕Y轴旋转的角速度：");
                sensorValues.append(values[1]);
                sensorValues.append("\n绕Z轴旋转的角速度：");
                sensorValues.append(values[2]);
                gyroTextView.setText(sensorValues.toString());

                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                //磁场传感器
                sensorValues = new StringBuilder();
                sensorValues.append("X轴方向上的磁场强度：");
                sensorValues.append(values[0]);
                sensorValues.append("\nY轴方向上的磁场强度：");
                sensorValues.append(values[1]);
                sensorValues.append("\nZ轴方向上的磁场强度：");
                sensorValues.append(values[2]);
                magneticTextView.setText(sensorValues.toString());

                break;
            case Sensor.TYPE_GRAVITY:
                //重力传感器
                sensorValues = new StringBuilder();
                sensorValues.append("X轴方向上的重力：");
                sensorValues.append(values[0]);
                sensorValues.append("\nY轴方向上的重力：");
                sensorValues.append(values[1]);
                sensorValues.append("\nZ轴方向上的重力：");
                sensorValues.append(values[2]);
                gravityTextView.setText(sensorValues.toString());

                break;
            case Sensor.TYPE_LINEAR_ACCELERATION:
                //线性加速度传感器
                sensorValues = new StringBuilder();
                sensorValues.append("X轴方向上的加速度：");
                sensorValues.append(values[0]);
                sensorValues.append("\nY轴方向上的加速度：");
                sensorValues.append(values[1]);
                sensorValues.append("\nZ轴方向上的加速度：");
                sensorValues.append(values[2]);
                linearTextView.setText(sensorValues.toString());

                break;
            case Sensor.TYPE_LIGHT:
                //光传感器
                sensorValues = new StringBuilder();
                sensorValues.append("当前光的强度为：");
                sensorValues.append(values[0]);
                lightTextView.setText(sensorValues.toString());

                break;
            default:
                break;
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
