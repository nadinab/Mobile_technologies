package com.example.lab2list;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class MainActivity extends AppCompatActivity {
    private ListView heroesList;
    public ArrayList<String> names;
    HeroesAPI heroesAPI;
    public ArrayList<HeroList> heroListinfo;
    private CompositeDisposable compositeDisposable;
    private ArrayList<Integer> imRes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        heroesList = (ListView) findViewById(R.id.heroes_list);
        names = new ArrayList<>();
        imRes = new ArrayList<>();

        heroListinfo = new ArrayList<>();
        compositeDisposable = new CompositeDisposable();
        configureRetrofit();
        compositeDisposable.add(heroesAPI.getHeroList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(x->{
                    heroListinfo = (ArrayList<HeroList>) x;
                    for(int i = 0; i < heroListinfo.size(); i++) {
                        names.add(heroListinfo.get(i).name);
                        imRes.add(getResources().getIdentifier(heroListinfo.get(i).localized_name, "drawable", this.getPackageName()));
                    }
                    HeroAdapter adapter = new HeroAdapter(this,names, imRes);
                    heroesList.setAdapter(adapter);
                    heroesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            toHeroActivity(heroListinfo.get(position));
                        }
                    });
                }));
    }

    public void toHeroActivity(HeroList hero){
        Intent intent = new Intent(this, HeroActivity.class);
        intent.putExtra("name", hero.name);
        intent.putExtra("attack_type", hero.attack_type);
        intent.putExtra("primary_attr", hero.primary_attr);
        intent.putExtra("legs", hero.legs);
        intent.putExtra("roles", hero.roles);
        intent.putExtra("image", getResources().getIdentifier(hero.localized_name, "drawable", this.getPackageName()));
        this.startActivity(intent);
    }

    private void configureRetrofit(){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.opendota.com/api/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        heroesAPI = retrofit.create(HeroesAPI.class);
    }

}