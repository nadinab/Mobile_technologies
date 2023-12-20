package com.example.lab4;

import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


public class About extends AppCompatActivity implements GestureOverlayView.OnGesturePerformedListener {
    GestureLibrary gLib;
    GestureOverlayView gestures;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_about);
        gLib = GestureLibraries.fromRawResource(this, R.raw.gestures);
        if (!gLib.load()) {
            //Если жесты не загружены, то выход из приложения
            finish();
        }

        gestures = (GestureOverlayView) findViewById(R.id.gestureViewAbout);
        gestures.addOnGesturePerformedListener(this);
    }

    @Override
    public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
        //Создаёт ArrayList c загруженными из gestures жестами
        java.util.ArrayList<Prediction> predictions = gLib.recognize(gesture);
        if (predictions.size() > 0) {

            //если загружен хотя бы один жест из gestures
            Prediction prediction = predictions.get(0);
            if (prediction.score > 1.0 && prediction.name.equals("close")) {
                finish();
            }
        }
    }
}
