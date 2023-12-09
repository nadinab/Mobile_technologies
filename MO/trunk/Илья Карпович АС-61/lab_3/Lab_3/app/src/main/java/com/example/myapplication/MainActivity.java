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
    int price;
    int image;
    boolean box;


    Product(String _describe, int _price, int _image, boolean _box) {
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
        for (int i = 1; i <= 1; i++) {
            products.add(new Product("Кружка 1", i * 199,
                    R.drawable.kr1, false));
            products.add(new Product("Кружка 2", i * 299,
                    R.drawable.kr2, false));
            products.add(new Product("Кружка 3", i * 399,
                    R.drawable.kr3, false));
            products.add(new Product("Кружка 4", i * 499,
                    R.drawable.kr4, false));
            products.add(new Product("Кружка 5", i * 599,
                    R.drawable.kr5, false));
            products.add(new Product("Кружка 6", i * 699,
                    R.drawable.kr6, false));
            products.add(new Product("Кружка 7", i * 799,
                    R.drawable.kr7, false));
            products.add(new Product("Кружка 8", i * 899,
                    R.drawable.kr8, false));
            products.add(new Product("Кружка 9", i * 999,
                    R.drawable.kr9, false));
            products.add(new Product("Кружка 10", i * 1099,
                    R.drawable.kr10, false));
            products.add(new Product("Кружка 11", i * 1199,
                    R.drawable.kr11, false));
            products.add(new Product("Кружка 12", i * 1299,
                    R.drawable.kr12, false));
        }
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
    
