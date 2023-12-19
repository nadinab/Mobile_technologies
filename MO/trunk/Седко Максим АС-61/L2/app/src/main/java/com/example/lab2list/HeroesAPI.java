package com.example.lab2list;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface HeroesAPI {
    @GET(value = "./heroes")
    @Headers("Content-Type: application/json")
    Single<List<HeroList>> getHeroList();
}
