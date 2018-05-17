package com.example.wagenhuberg.android_180505_entfernungsmesser;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

//Hinweis: Dieses Projekt gibt es von Google auch als fertige Library z.B. für Apps die Telefonie implementieren, und dabei den Touch sperren möchten!

public class MainActivity extends Activity implements SensorEventListener {

    private Helligkeit helligkeit;
    private TextView textView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);

        helligkeit = new Helligkeit();

        textView = findViewById(R.id.helligkeit);
        button = findViewById(R.id.button);

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
                //System.out.println(helligkeit);
                textView.setText(helligkeit.toString());
                if (aenderung < 0.1) {
                    aktiviereTouchscreen(false, "Touchscreen deaktiviert");
                } else if (aenderung > 6.5) {
                aktiviereTouchscreen(true, "Touchscreen aktiviert");
                }
            }

        }
    }

    private void aktiviereTouchscreen(boolean aktiviere, String message) {
        if (aktiviere) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        } else {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        }

        System.out.println(message);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
