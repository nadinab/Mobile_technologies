package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.media.MediaScannerConnection;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class gallery extends AppCompatActivity implements GestureOverlayView.OnGesturePerformedListener{
    GestureLibrary gLib;
    GestureOverlayView gestures;
    private MediaScannerConnection mediaScannerConnection;
    private Integer[] pictures = {R.drawable.one, R.drawable.two, R.drawable.three, R.drawable.four,
            R.drawable.five,R.drawable.six,R.drawable.seven,R.drawable.eight,R.drawable.nine};
    private int curIndex = 0;
    ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        iv = (ImageView)findViewById(R.id.imageView1);
        iv.setImageResource(pictures[curIndex]);
        gLib = GestureLibraries.fromRawResource(this, R.raw.gestures);
        if (!gLib.load()) {
            //Если жесты не загружены, то выход из приложения
            finish();
        }

        gestures = (GestureOverlayView) findViewById(R.id.gestureViewGallery);
        gestures.addOnGesturePerformedListener(this);
    }
    public void previous(View view){
        if(curIndex == 0){
            curIndex = pictures.length - 1;
            iv.setImageResource(pictures[curIndex]);
        }
        else{
            curIndex--;
            iv.setImageResource(pictures[curIndex]);
        }
    }
    public void next(View view){
        if(curIndex == pictures.length-1){
            curIndex = 0;
            iv.setImageResource(pictures[curIndex]);
        }
        else{
            curIndex++;
            iv.setImageResource(pictures[curIndex]);
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