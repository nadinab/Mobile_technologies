package by.brstu.redlabrat.testlistapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import by.brstu.redlabrat.testlistapp.api.OmdbSearchResultMovie;

public class MainActivity extends AppCompatActivity implements MyListAdapter.MyItemClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Fragment fragment = ListFragment.newInstance("");
        getSupportFragmentManager().beginTransaction()
                .add(R.id.mainFrame, fragment).commit();
    }

    @Override
    public void onMyItemClick(OmdbSearchResultMovie selectedMovie) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.mainFrame, DetailsFragment.newInstance(selectedMovie.title))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void addToFavorites(OmdbSearchResultMovie selectedMovie) {
        TestListApp app = (TestListApp) getApplication();

        Thread thread = new Thread(){
            @Override
            public void run() {
                super.run();
                if (app.omdbDao.getMovieById(selectedMovie.imdbId) != null) {
                    app.omdbDao.removeFromFavorites(selectedMovie);
                } else {
                    app.omdbDao.addMovieToFavorites(selectedMovie);
                }
            }
        };

        thread.start();
    }

    void goToFavorites() {
        getSupportFragmentManager().beginTransaction()
            .replace(R.id.mainFrame, new FavoritesListFragment())
            .addToBackStack(null)
            .commit();
    }
}