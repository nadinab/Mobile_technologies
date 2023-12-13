package com.bstu.ryzzax.lab2secondedition;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bstu.ryzzax.lab2secondedition.models.UserModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InitialFragment extends Fragment {
    
    TextView mText;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_initial, container, false);
    }
    
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        Button btn = getView().findViewById(R.id.req_btn);
        btn.setOnClickListener(this::handleGetClick);
        mText = getView().findViewById(R.id.downloading_text);
        
    }
    
    private void handleGetClick(View view) {
        Toast.makeText(getView().getContext(), "Downloading", Toast.LENGTH_SHORT).show();
        getProducts();
    }
    
    private void getProducts() {
        Call<List<UserModel>> call =
                RetrofitClient.getInstance().getMyApi().getProducts();
        call.enqueue(new Callback<List<UserModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<UserModel>> call,
                                   @NonNull Response<List<UserModel>> response) {
                
                assert response.body() != null;
                List<UserModel> products = response.body();
                
                Bundle args = new Bundle();
                args.putParcelableArrayList("values", (ArrayList<UserModel>) products);
                
                Constants.MAIN_ACTIVITY.mNavController.navigate(R.id.action_initialFragment_to_productListFragment, args);
            }
            
            @Override
            public void onFailure(Call<List<UserModel>> call, Throwable t) {
                
                Toast.makeText(getView().getContext(), "Bad request", Toast.LENGTH_SHORT).show();
                Log.e("TAG", t.getMessage());
            }
            
        });
    }
    
}