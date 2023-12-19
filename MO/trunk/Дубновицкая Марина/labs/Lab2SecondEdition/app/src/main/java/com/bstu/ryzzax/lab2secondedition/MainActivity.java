package com.bstu.ryzzax.lab2secondedition;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.bstu.ryzzax.lab2secondedition.models.ProductModel;
import com.bstu.ryzzax.lab2secondedition.models.ResponseModel;
import com.bstu.ryzzax.lab2secondedition.utils.Constants;
import com.bstu.ryzzax.lab2secondedition.utils.RetrofitClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {
    
    public NavController mNavController;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Constants.MAIN_ACTIVITY = this;
        
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        mNavController = navHostFragment.getNavController();
    
        Toast.makeText(this, "Разработала Марина Дубновицкая", Toast.LENGTH_SHORT).show();
        
        
    }
    
}