package com.example.wagenhuberg.android_180505_entfernungsmesser;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity implements SensorEventListener {

    private Helligkeit helligkeit;
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);

        helligkeit = new Helligkeit();

        textView = findViewById(R.id.helligkeit);


    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        double neueHelligkeit = sensorEvent.values[0];
        if (neueHelligkeit > 0) {
            double alteHelligkeit = helligkeit.getHelligkeit();
            helligkeit.setHelligkeit(neueHelligkeit);

            if (alteHelligkeit > 0) {
                double aenderung = neueHelligkeit / alteHelligkeit;
                helligkeit.setAenderungsquotient(aenderung);

                if (aenderung < helligkeit.getKleinsteAenderung()) {
                    helligkeit.setKleinsteAenderung(aenderung);
                } else if (aenderung > helligkeit.getGroessteAenderung()) {
                    helligkeit.setGroessteAenderung(aenderung);
                }
                System.out.println(helligkeit);
                textView.setText(helligkeit.toString());
            }

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
