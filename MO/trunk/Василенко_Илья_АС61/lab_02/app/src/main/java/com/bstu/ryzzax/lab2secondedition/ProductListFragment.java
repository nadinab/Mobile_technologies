package com.bstu.ryzzax.lab2secondedition;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.bstu.ryzzax.lab2secondedition.models.ProductModel;
import com.bstu.ryzzax.lab2secondedition.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class ProductListFragment extends Fragment {
    
    private List<ProductModel> mProductList = new ArrayList<>();
    
    
    public ProductListFragment() {
    }
    
    @SuppressWarnings("unused")
    public static ProductListFragment newInstance(List<ProductModel> values) {
        ProductListFragment fragment = new ProductListFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList("values", (ArrayList<? extends Parcelable>) values);
        fragment.setArguments(args);
        return fragment;
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mProductList =
                    getArguments().getParcelableArrayList("values");
            
        }
        
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_list, container, false);
        
        // Set the adapter
        return view;
    }
    
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            ProductListAdapter adapter = new ProductListAdapter(new ArrayList<ProductModel>());
            adapter.setOnProductClickListener(this::handleProductClick);
            
            adapter.setValues(mProductList);
            
            recyclerView.setAdapter(adapter);
            
        }
        
        
    }
    
    private void handleProductClick(ProductModel product, int position) {
        Bundle args = new Bundle();
        args.putParcelable("product", product);
        Constants.MAIN_ACTIVITY.mNavController.navigate(R.id.action_productListFragment_to_detailsFragment, args);
    }
}