package com.bstu.ryzzax.lab2secondedition;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bstu.ryzzax.lab2secondedition.models.PostModel;


public class DetailsFragment extends Fragment {
    
    TextView mDetailsView;
    PostModel mProduct;
    
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
    
    public void setProduct(PostModel model) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Название: ");
        stringBuilder.append(model.getTitle());
        
        stringBuilder.append("\nИдентификатор поста: ");
        stringBuilder.append(model.getId());
        
        stringBuilder.append("\nИдентификатор пользователя: ");
        stringBuilder.append(model.getUserId());
        
        stringBuilder.append("\nНазвание статьи: ");
        stringBuilder.append(model.getTitle());
        
        stringBuilder.append("\n\nТекст: ");
        stringBuilder.append(model.getBody());
        
        String details = stringBuilder.toString();
        
        mDetailsView.setText(details);
    }
    
}