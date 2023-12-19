package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
class Product implements Serializable {

    String name;
    String price;
    int image;
    boolean box;


    Product(String _describe, String _price, int _image, boolean _box) {
        name = _describe;
        price = _price;
        image = _image;
        box = _box;
    }
}

public class MainActivity extends Activity {

    ArrayList<Product> products = new ArrayList<>();
    ArrayList<Product> favProducts = new ArrayList<>();
    BoxAdapter boxAdapter;
    int count;
    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=(TextView)findViewById(R.id.textView2);
        tv.setText(Integer.toString(count));
        // создаем адаптер
        fillData();
        boxAdapter = new BoxAdapter(this, products);

        // настраиваем список
        ListView lvMain = (ListView) findViewById(R.id.lvSecond);
        lvMain.setAdapter(boxAdapter);
    }

    // генерируем данные для адаптера
    void fillData() {
            products.add(new Product("lipstick ", "3500",
                    R.drawable.a, false));
            products.add(new Product("heavy metal ", "3500",
                    R.drawable.b, false));
            products.add(new Product("trivial  ", "3500",
                    R.drawable.c, false));
            products.add(new Product("poison ", "3500",
                    R.drawable.d, false));
            products.add(new Product("thriller ", "3500",
                    R.drawable.e, false));
    }

    public void clickOpenFav(View view) {
        favProducts.clear();
        Intent intent = new Intent(this, SecondActivity.class);
        for (Product p : boxAdapter.getBox()) {
            if (p.box)
            {
                favProducts.add(p);
            }
        }
        intent.putExtra("list", favProducts);
        startActivity(intent);
    }
    public TextView tv;
    public void onCheckboxClicked(View view)
    {
        tv=(TextView)findViewById(R.id.textView2);
        CheckBox cb = (CheckBox)view;
        if (cb.isChecked())
            count++;
        else
            count--;
        tv.setText(Integer.toString(count));
    }
}
    
