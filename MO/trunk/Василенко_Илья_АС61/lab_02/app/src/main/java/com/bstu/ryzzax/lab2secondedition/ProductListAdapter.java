package com.bstu.ryzzax.lab2secondedition;

import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bstu.ryzzax.lab2secondedition.models.ProductModel;
import com.bstu.ryzzax.lab2secondedition.databinding.FragmentProductListItemBinding;

import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {
    
    interface OnProductClickListener {
        void onProductClick(ProductModel product, int position);
    }
    
    private OnProductClickListener mOnProductClickListener;
    
    public void setOnProductClickListener(OnProductClickListener onProductClickListener) {
        mOnProductClickListener = onProductClickListener;
    }
    
    private final List<ProductModel> mValues;
    
    public ProductListAdapter(List<ProductModel> items) {
        mValues = items;
    }
    
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        
        return new ViewHolder(FragmentProductListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
        
    }
    
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).getTitle());
        holder.mPriceView.setText(String.valueOf(mValues.get(position).getPrice()));
        
        holder.itemView.setOnClickListener(view ->
                mOnProductClickListener.onProductClick(holder.mItem, position));
    }
    
    @Override
    public int getItemCount() {
        return mValues.size();
    }
    
    @SuppressLint("NotifyDataSetChanged")
    public void setValues(List<ProductModel> products) {
        mValues.clear();
        mValues.addAll(products);
        notifyDataSetChanged();
    }
    
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mIdView;
        public final TextView mPriceView;
        public ProductModel mItem;
        
        public ViewHolder(FragmentProductListItemBinding binding) {
            super(binding.getRoot());
            mIdView = binding.itemName;
            mPriceView = binding.itemPrice;
        }
        
        @Override
        public String toString() {
            return super.toString() + " '" + mPriceView.getText() + "'";
        }
    }
}