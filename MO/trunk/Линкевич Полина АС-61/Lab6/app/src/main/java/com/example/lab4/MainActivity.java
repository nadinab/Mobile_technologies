package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.content.Intent;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements GestureOverlayView.OnGesturePerformedListener {

    ImageButton imgBtn;
    GestureLibrary gLib;
    GestureOverlayView gestures;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgBtn = findViewById(R.id.imageButton);

        gLib = GestureLibraries.fromRawResource(this, R.raw.gestures);

        if (!gLib.load()) {
            //Если жесты не загружены, то выход из приложения
            finish();
        }

        gestures = (GestureOverlayView) findViewById(R.id.gestureView);
        gestures.addOnGesturePerformedListener(this);
    }

    public void toVideo(View view) {
        Intent intent = new Intent(this, video.class);
        startActivity(intent);
    }

    public void toAudio(View view) {
        Intent intent = new Intent(this, Audio.class);
        startActivity(intent);
    }

    public void toCamera(View view) {
        Intent intent = new Intent(this, Camera.class);
        startActivity(intent);
    }

    public void toGallery(View view) {
        Intent intent = new Intent(this, gallery.class);
        startActivity(intent);
    }

    public void toAbout(View view) {
        Intent intent = new Intent(this, About.class);
        startActivity(intent);
    }

    public void toVideo() {
        Intent intent = new Intent(this, video.class);
        startActivity(intent);
    }

    public void toAudio() {
        Intent intent = new Intent(this, Audio.class);
        startActivity(intent);
    }

    public void toCamera() {
        Intent intent = new Intent(this, Camera.class);
        startActivity(intent);
    }

    public void toGallery() {
        Intent intent = new Intent(this, gallery.class);
        startActivity(intent);
    }

    public void toAbout() {
        Intent intent = new Intent(this, About.class);
        startActivity(intent);
    }

    public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
        //Создаёт ArrayList c загруженными из gestures жестами
        ArrayList<Prediction> predictions = gLib.recognize(gesture);
        if (predictions.size() > 0) {

            //если загружен хотя бы один жест из gestures
            Prediction prediction = predictions.get(0);
            if (prediction.score > 1) {
                switch (prediction.name) {
                    case "about":
                        Toast.makeText(getApplicationContext(), "Recognized: ABOUT", Toast.LENGTH_SHORT).show();
                        toAbout();
                        break;
                    case "video":
                        Toast.makeText(getApplicationContext(), "Recognized: VIDEO", Toast.LENGTH_SHORT).show();
                        toVideo();
                        break;
                    case "audio":
                        Toast.makeText(getApplicationContext(), "Recognized: AUDIO", Toast.LENGTH_SHORT).show();
                        toAudio();
                        break;
                    case "camera":
                        Toast.makeText(getApplicationContext(), "Recognized: CAMERA", Toast.LENGTH_SHORT).show();
                        toCamera();
                    case "gallery":
                        Toast.makeText(getApplicationContext(), "Recognized: GALLERY", Toast.LENGTH_SHORT).show();
                        toGallery();
                        break;
                    case "close":
                        Toast.makeText(getApplicationContext(), "Recognized: CLOSE", Toast.LENGTH_SHORT).show();
                        finish();
                        break;
                }
            }
        }
    }
}