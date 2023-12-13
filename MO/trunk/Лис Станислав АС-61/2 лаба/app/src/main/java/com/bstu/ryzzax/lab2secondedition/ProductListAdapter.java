package com.bstu.ryzzax.lab2secondedition;

import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bstu.ryzzax.lab2secondedition.databinding.FragmentProductListItemBinding;

import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {
    
    interface OnProductClickListener {
        void onProductClick(CommentModel product, int position);
    }
    
    private OnProductClickListener mOnProductClickListener;
    
    public void setOnProductClickListener(OnProductClickListener onProductClickListener) {
        mOnProductClickListener = onProductClickListener;
    }
    
    private final List<CommentModel> mValues;
    
    public ProductListAdapter(List<CommentModel> items) {
        mValues = items;
    }
    
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        
        return new ViewHolder(FragmentProductListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
        
    }
    
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mNameView.setText(mValues.get(position).getName());
        holder.mIdView.setText(String.valueOf(holder.mItem.getPostId()));
        holder.mEmailView.setText(holder.mItem.getEmail());
        
        holder.itemView.setOnClickListener(view ->
                mOnProductClickListener.onProductClick(holder.mItem, position));
    }
    
    @Override
    public int getItemCount() {
        return mValues.size();
    }
    
    @SuppressLint("NotifyDataSetChanged")
    public void setValues(List<CommentModel> products) {
        mValues.clear();
        mValues.addAll(products);
        notifyDataSetChanged();
    }
    
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mNameView;
        public final TextView mIdView;
        public final TextView mEmailView;
        public CommentModel mItem;
        
        public ViewHolder(FragmentProductListItemBinding binding) {
            super(binding.getRoot());
            mNameView = binding.itemName;
            mIdView = binding.itemId;
            mEmailView = binding.itemEmail;
            
        }
        
    }
}