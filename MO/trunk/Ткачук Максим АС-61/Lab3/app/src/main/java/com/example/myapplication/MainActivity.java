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
            products.add(new Product("Iphon", i * 199,
                    R.drawable.iphon, false));
            products.add(new Product("Iphon 3G", i * 299,
                    R.drawable.iphon3g, false));
            products.add(new Product("Iphon 4", i * 399,
                    R.drawable.iphon4, false));
            products.add(new Product("Iphon 5S", i * 499,
                    R.drawable.iphon5s, false));
            products.add(new Product("Iphon 6", i * 599,
                    R.drawable.iphon6, false));
            products.add(new Product("Iphon 7", i * 699,
                    R.drawable.iphon7, false));
            products.add(new Product("Iphon 8", i * 799,
                    R.drawable.iphon8, false));
            products.add(new Product("Iphon 10", i * 899,
                    R.drawable.iphon10, false));
            products.add(new Product("Iphon 11", i * 999,
                    R.drawable.iphon11, false));
            products.add(new Product("Iphon 12", i * 1099,
                    R.drawable.iphon12, false));
            products.add(new Product("Iphon 13", i * 1199,
                    R.drawable.iphon13, false));
            products.add(new Product("Iphon 14", i * 1299,
                    R.drawable.iphon14, false));
            products.add(new Product("Iphon 15", i * 1399,
                    R.drawable.iphon15, false));
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
    
