package com.example.myapp;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class ImageActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private ImageAdapter imageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        // Initialize the view pager
        viewPager = findViewById(R.id.view_pager);

        // Create an image adapter with the image ids
        imageAdapter = new ImageAdapter(this, new int[]{R.drawable.image1, R.drawable.image2, R.drawable.image3});

        // Set the adapter to the view pager
        viewPager.setAdapter(imageAdapter);
    }
}
