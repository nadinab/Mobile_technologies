package by.brstu.redlabrat.testlistapp.api;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "search_result_movie")
public class OmdbSearchResultMovie {

    @SerializedName("Title")
    @ColumnInfo(name = "title")
    public String title;

    @SerializedName("Year")
    @ColumnInfo(name = "year")
    public String year;

    @PrimaryKey
    @SerializedName("imdbID")
    @ColumnInfo(name = "imdbId")
    @NonNull
    public String imdbId;

    @SerializedName("Type")
    @ColumnInfo(name = "type")
    public String type;

    @SerializedName("Poster")
    @ColumnInfo(name = "posterUrl")
    public String posterUrl;
}