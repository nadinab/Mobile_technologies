package com.bstu.ryzzax.lab2secondedition;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class DetailsFragment extends Fragment {
    
    TextView mDetailsView;
    CommentModel mProduct;
    
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
    
    public void setProduct(CommentModel model) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Отправитель: ");
        stringBuilder.append(model.getEmail());
    
        stringBuilder.append("\nЗаголовок: ");
        stringBuilder.append(model.getName());
    
    
    
        stringBuilder.append("\n\nТекст сообщения: ");
        stringBuilder.append(model.getBody());
        
        String details = stringBuilder.toString();
        
        mDetailsView.setText(details);
    }
    
}