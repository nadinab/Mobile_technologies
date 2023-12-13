package com.bstu.ryzzax.lab2secondedition;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    String BASE_URL = "https://jsonplaceholder.typicode.com";
    
    @GET("comments")
    Call<List<CommentModel>> getProducts();
}
