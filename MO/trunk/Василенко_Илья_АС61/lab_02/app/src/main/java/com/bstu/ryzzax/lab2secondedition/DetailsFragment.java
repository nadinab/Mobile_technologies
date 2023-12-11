package com.bstu.ryzzax.lab2secondedition;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bstu.ryzzax.lab2secondedition.models.ProductModel;


public class DetailsFragment extends Fragment {
    
    TextView mDetailsView;
    ProductModel mProduct;
    
    public DetailsFragment() {
        // Required empty public constructor
    }
    
    public static DetailsFragment newInstance() {
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mProduct =
                    getArguments().getParcelable("product");
        }
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false);
    }
    
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        mDetailsView = view.findViewById(R.id.details_textview);
        
        setProduct(mProduct);
    }
    
    public void setProduct(ProductModel product) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Название: ");
        stringBuilder.append(product.getTitle());
        
        stringBuilder.append("\nБренд: ");
        stringBuilder.append(product.getBrand());
        
        stringBuilder.append("\nОписание: ");
        stringBuilder.append(product.getDescription());
        
        stringBuilder.append("\nКатегория: ");
        stringBuilder.append(product.getCategory());
        
        stringBuilder.append("\nСкидка: ");
        stringBuilder.append(product.getDiscount()).append("%");
        
        stringBuilder.append("\nРейтинг: ");
        stringBuilder.append(product.getRating()).append("/5");
        
        stringBuilder.append("\nВ наличии: ");
        stringBuilder.append(product.getStock()).append(" items");
        
        stringBuilder.append("\nЦена: ");
        stringBuilder.append(product.getPrice()).append("$");
        
        String details = stringBuilder.toString();
        
        mDetailsView.setText(details);
    }
    
}