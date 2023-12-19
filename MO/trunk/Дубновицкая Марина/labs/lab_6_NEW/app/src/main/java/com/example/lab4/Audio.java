package com.example.lab4;

//import android.media.MediaPlayer;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.Toast;
//
//public class Audio {
//}


import androidx.appcompat.app.AppCompatActivity;

import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Audio extends AppCompatActivity implements GestureOverlayView.OnGesturePerformedListener {
    GestureLibrary gLib;
    GestureOverlayView gestures;

    MediaPlayer mPlayer;
    Button playButton, pauseButton, stopButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);

        mPlayer=MediaPlayer.create(this, R.raw.melody);
        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                stopPlay();
            }
        });

        playButton = findViewById(R.id.playButton);
        pauseButton = findViewById(R.id.pauseButton);
        stopButton = findViewById(R.id.stopButton);
        gLib = GestureLibraries.fromRawResource(this, R.raw.gestures);
        if (!gLib.load()) {
            //Если жесты не загружены, то выход из приложения
            finish();
        }

        gestures = (GestureOverlayView) findViewById(R.id.gestureViewAudio);
        gestures.addOnGesturePerformedListener(this);
    }
    private void stopPlay(){
        mPlayer.stop();
        try {
            mPlayer.prepare();
            mPlayer.seekTo(0);
            playButton.setEnabled(true);
        }
        catch (Throwable t) {
            Toast.makeText(this, t.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    public void play(View view){

        mPlayer.start();

    }
    public void pause(View view){

        mPlayer.pause();
    }
    public void stop(View view){
        stopPlay();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPlayer.isPlaying()) {
            stopPlay();
        }
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