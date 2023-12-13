package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button cameraButton;
    private Button audioVideoButton;
    private Button imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the buttons
        cameraButton = findViewById(R.id.camera_button);
        audioVideoButton = findViewById(R.id.audio_video_button);
        imageButton = findViewById(R.id.image_button);

        // Set the listeners for the buttons
        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the camera activity
                Intent intent = new Intent(MainActivity.this, CameraActivity.class);
                startActivity(intent);
            }
        });

        audioVideoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the audio video activity
                Intent intent = new Intent(MainActivity.this, AudioVideoActivity.class);
                startActivity(intent);
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the image activity
                Intent intent = new Intent(MainActivity.this, ImageActivity.class);
                startActivity(intent);
            }
        });
    }
}
