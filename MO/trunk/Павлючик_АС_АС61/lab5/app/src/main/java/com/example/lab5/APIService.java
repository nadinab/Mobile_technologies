package com.example.lab5;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface APIService {
    @Streaming
    @GET
    Call<ResponseBody> downloadFile(@Url String fileUrl);
}
