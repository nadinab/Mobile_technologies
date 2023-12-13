package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class about_me extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me);
    }

    public void play(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void developer(View view){
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }
}