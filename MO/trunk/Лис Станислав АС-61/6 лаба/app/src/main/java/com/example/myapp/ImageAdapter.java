package com.example.myapp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class ImageAdapter extends PagerAdapter {

    private Context context;
    private int[] imageIds;

    public ImageAdapter(Context context, int[] imageIds) {
        this.context = context;
        this.imageIds = imageIds;
    }

    @Override
    public int getCount() {
        return imageIds.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        // Create an image view for the image
        ImageView imageView = new ImageView(context);
        imageView.setImageResource(imageIds[position]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        // Add the image view to the container
        container.addView(imageView);

        // Return the image view as the object
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        // Remove the image view from the container
        container.removeView((ImageView) object);
    }
}
