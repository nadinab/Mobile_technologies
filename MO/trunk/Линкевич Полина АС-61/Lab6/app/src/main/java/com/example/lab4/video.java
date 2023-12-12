package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.VideoView;

public class video extends AppCompatActivity implements GestureOverlayView.OnGesturePerformedListener {
    GestureLibrary gLib;
    GestureOverlayView gestures;
    VideoView videoPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        videoPlayer = (VideoView) findViewById(R.id.videoPlayer);
        Uri myVIdeoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.test);
        videoPlayer.setVideoURI(myVIdeoUri);

        gLib = GestureLibraries.fromRawResource(this, R.raw.gestures);
        if (!gLib.load()) {
            //Если жесты не загружены, то выход из приложения
            finish();
        }

        gestures = (GestureOverlayView) findViewById(R.id.gestureViewVideo);
        gestures.addOnGesturePerformedListener(this);
    }
    public void play(View view){
        videoPlayer.start();
    }
    public void pause(View view){
        videoPlayer.pause();
    }
    public void stop(View view){
        videoPlayer.stopPlayback();
        videoPlayer.resume();
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