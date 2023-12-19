package com.bstu.ryzzax.lab2secondedition;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    
    public NavController mNavController;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Constants.MAIN_ACTIVITY = this;
        
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        mNavController = navHostFragment.getNavController();
    
        Toast.makeText(this, "Рыжко Сергей", Toast.LENGTH_SHORT).show();
        
        
    }
    
}