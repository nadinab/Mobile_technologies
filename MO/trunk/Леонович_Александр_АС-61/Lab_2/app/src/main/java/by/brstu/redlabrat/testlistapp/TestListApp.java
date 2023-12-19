package by.brstu.redlabrat.testlistapp;

import android.app.Application;
import androidx.room.Room;
import by.brstu.redlabrat.testlistapp.api.OmdbApi;
import by.brstu.redlabrat.testlistapp.db.MovieDao;
import by.brstu.redlabrat.testlistapp.db.MoviesDatabase;
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TestListApp extends Application {

    public OmdbApi omdbApi;
    public MovieDao omdbDao;

    @Override
    public void onCreate() {
        super.onCreate();

        omdbApi = new Retrofit.Builder()
                .baseUrl("https://www.omdbapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(OmdbApi.class);

        omdbDao = Room.databaseBuilder(
                getApplicationContext(),
                MoviesDatabase.class,
                "favorite_movies.db")
            .build()
                .getFavoritesDao();
    }
}