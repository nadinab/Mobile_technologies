package by.brstu.redlabrat.testlistapp.api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OmdbSearchResult{

    @SerializedName("Search")
    public List<OmdbSearchResultMovie> results;

    @SerializedName("totalResults")
    public String totalResults;
}