package by.brstu.redlabrat.testlistapp.api;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OmdbApi {

    @GET("/")
    Single<OmdbSearchResult> getResultsForString(
            @Query("s") String partialName,
            @Query("apikey") String apiKey
    );
}