package com.bstu.ryzzax.lab2secondedition.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class ProductModel implements Parcelable {
    private final String id;
    private final String title;
    private final String description;
    private final String brand;
    private final String category;
    private final double price;
    private final double discount;
    private final double rating;
    private final double stock;
    
    protected ProductModel(Parcel in) {
        id = in.readString();
        title = in.readString();
        description = in.readString();
        brand = in.readString();
        category = in.readString();
        price = in.readDouble();
        discount = in.readDouble();
        rating = in.readDouble();
        stock = in.readDouble();
    }
    
    public static final Creator<ProductModel> CREATOR = new Creator<ProductModel>() {
        @Override
        public ProductModel createFromParcel(Parcel in) {
            return new ProductModel(in);
        }
        
        @Override
        public ProductModel[] newArray(int size) {
            return new ProductModel[size];
        }
    };
    
    public String getId() {
        return id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public String getDescription() {
        return description;
    }
    
    public String getBrand() {
        return brand;
    }
    
    public String getCategory() {
        return category;
    }
    
    public double getPrice() {
        return price;
    }
    
    public double getDiscount() {
        return discount;
    }
    
    public double getRating() {
        return rating;
    }
    
    public double getStock() {
        return stock;
    }
    
    
    @Override
    public int describeContents() {
        return 0;
    }
    
    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(brand);
        dest.writeString(category);
        dest.writeDouble(price);
        dest.writeDouble(discount);
        dest.writeDouble(rating);
        dest.writeDouble(stock);
    }
}
