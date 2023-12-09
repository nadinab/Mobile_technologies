package com.bstu.ryzzax.lab2secondedition.utils;

import com.bstu.ryzzax.lab2secondedition.models.PostModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    String BASE_URL = "https://jsonplaceholder.typicode.com";
    
    @GET("posts")
    Call<List<PostModel>> getProducts();
}
