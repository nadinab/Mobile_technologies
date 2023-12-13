package by.brstu.redlabrat.testlistapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

import by.brstu.redlabrat.testlistapp.api.OmdbSearchResult;
import by.brstu.redlabrat.testlistapp.api.OmdbSearchResultMovie;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.core.SingleOnSubscribe;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FavoritesListFragment extends Fragment {

    private MyListAdapter listAdapter = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorites_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = getActivity().findViewById(R.id.recyclerViewFavorites);

        TestListApp app = (TestListApp) getActivity().getApplication();

        listAdapter = new MyListAdapter(Collections.emptyList(),
                app.omdbDao,
                (MyListAdapter.MyItemClickListener)getActivity());

        recyclerView.setAdapter(listAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        Single.create((SingleOnSubscribe<List<OmdbSearchResultMovie>>) emitter -> {
            List <OmdbSearchResultMovie> list = app.omdbDao.getAllSavedMovies();
            emitter.onSuccess(list);
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new SingleObserver<List<OmdbSearchResultMovie>>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull List<OmdbSearchResultMovie> omdbSearchResultMovies) {
                        listAdapter.setNewListOfItems(omdbSearchResultMovies);
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                    }
                });
    }
}