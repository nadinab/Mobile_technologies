package com.bstu.ryzzax.lab2secondedition.utils;

import com.bstu.ryzzax.lab2secondedition.models.ResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    String BASE_URL = "https://dummyjson.com/";
    
    @GET("products")
    Call<ResponseModel> getProducts();
    
}
