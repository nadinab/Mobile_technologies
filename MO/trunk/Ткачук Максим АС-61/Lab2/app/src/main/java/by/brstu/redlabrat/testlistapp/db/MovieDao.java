package by.brstu.redlabrat.testlistapp.db;

import androidx.room.*;

import java.util.List;

import by.brstu.redlabrat.testlistapp.api.OmdbSearchResultMovie;

@Dao
public interface MovieDao {

    @Query("select * from search_result_movie")
    List<OmdbSearchResultMovie> getAllSavedMovies();

    @Query("select * from search_result_movie where imdbId == :id")
    OmdbSearchResultMovie getMovieById(String id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addMovieToFavorites(OmdbSearchResultMovie movie);

    @Delete
    void removeFromFavorites(OmdbSearchResultMovie movie);
}