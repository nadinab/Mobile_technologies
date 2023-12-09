package com.example.lab2list;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class HeroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero);

        Intent info = getIntent();
        String name = info.getStringExtra("name");
        String attack_type = info.getStringExtra("attack_type");
        String primary_attr = info.getStringExtra("primary_attr");
        String legs = String.valueOf(info.getIntExtra("legs",0));
        String[] roles = info.getStringArrayExtra("roles");
        int image = info.getIntExtra("image", 0);
        ImageView imageView = (ImageView) findViewById(R.id.heroactivity_image);
        TextView text_name = (TextView) findViewById(R.id.heroactivity_name);
        TextView text_attack_type = (TextView) findViewById(R.id.heroactivity_attack_type);
        TextView text_primary_attr = (TextView) findViewById(R.id.heroactivity_primary_attr);
        TextView text_legs = (TextView) findViewById(R.id.heroactivity_legs);
        TextView text_roles = (TextView) findViewById(R.id.heroactivity_roles);

        imageView.setImageResource(image);
        text_name.setText(name);
        text_attack_type.setText("Attack_type: "+attack_type);
        text_primary_attr.setText("Primary_attr: "+primary_attr);
        text_legs.setText("Legs: "+legs);
        String text = "Roles:\n";
        for(int i = 0; i< roles.length; i++) text = text + (i+1)+"."+ roles[i] + ";\n";
        text_roles.setText(text);
        Log.i("TAGGGGGGGG", name);
        Log.i("TAGGGGGGGG", attack_type);
        Log.i("TAGGGGGGGG", primary_attr);
        Log.i("TAGGGGGGGG", legs);
        Log.i("TAGGGGGGGG", text);

    }
}