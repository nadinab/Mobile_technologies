package com.example.lab2list;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class HeroAdapter extends ArrayAdapter<String> {

    private Context context;
    private ArrayList<String> names;
    private ArrayList<Integer> images;

    public HeroAdapter(Context context, ArrayList<String> names, ArrayList<Integer> images){
        super(context, R.layout.hero, names);
        this.context = context;
        this.names = names;
        this.images = images;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.hero, parent,false);
        TextView name = (TextView) view.findViewById(R.id.hero_name);
        name.setText(this.names.get(position));
        ImageView imageView = (ImageView) view.findViewById(R.id.hero_image);
        imageView.setImageResource(images.get(position));
        return view;
    }
}
