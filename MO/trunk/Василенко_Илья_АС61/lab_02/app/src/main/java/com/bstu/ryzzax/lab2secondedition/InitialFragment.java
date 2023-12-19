package com.bstu.ryzzax.lab2secondedition;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bstu.ryzzax.lab2secondedition.models.ProductModel;
import com.bstu.ryzzax.lab2secondedition.models.ResponseModel;
import com.bstu.ryzzax.lab2secondedition.utils.Constants;
import com.bstu.ryzzax.lab2secondedition.utils.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

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
        Call<ResponseModel> call = RetrofitClient.getInstance().getMyApi().getProducts();
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, retrofit2.Response<ResponseModel> response) {
                
                assert response.body() != null;
                List<ProductModel> products = response.body().getProducts();
                
                Bundle args = new Bundle();
                args.putParcelableArrayList("values", (ArrayList<ProductModel>) products);
                
                Constants.MAIN_ACTIVITY.mNavController.navigate(R.id.action_initialFragment_to_productListFragment, args);
                
                
            }
            
            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                
                Toast.makeText(getView().getContext(), "Bad request", Toast.LENGTH_SHORT).show();
            }
            
        });
    }
    
}