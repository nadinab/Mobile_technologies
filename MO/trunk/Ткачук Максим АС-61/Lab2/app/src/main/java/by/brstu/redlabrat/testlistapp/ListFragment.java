package by.brstu.redlabrat.testlistapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;

import by.brstu.redlabrat.testlistapp.api.OmdbSearchResult;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ListFragment extends Fragment {

    private String argument1 = null;
    private MyListAdapter listAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        argument1 = getArguments().getString(ARG_PARAM1);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TestListApp app = (TestListApp) getActivity().getApplication();
        RecyclerView recyclerView = getActivity().findViewById(R.id.recyclerView);
        EditText searchText = ((EditText)getActivity().findViewById(R.id.searchText));
        ImageButton goToFavoritesButton = ((ImageButton)getActivity().findViewById(R.id.goToFavoritesButton));

        listAdapter = new MyListAdapter(Collections.emptyList(),
                app.omdbDao,
                (MyListAdapter.MyItemClickListener)getActivity());

        recyclerView.setAdapter(listAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        ((Button)getActivity().findViewById(R.id.searchButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                app.omdbApi.getResultsForString(searchText.getText().toString(),  "c8d2a36b")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new SingleObserver<OmdbSearchResult>() {
                            @Override
                            public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                            }

                            @Override
                            public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull OmdbSearchResult omdbSearchResult) {
                                listAdapter.setNewListOfItems(omdbSearchResult.results);
                            }

                            @Override
                            public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                            }
                        });
            }
        });

        goToFavoritesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)(getActivity())).goToFavorites();
            }
        });
    }

    private static String ARG_PARAM1 = "argument parameter 1";

    static Fragment newInstance(String param1){
        Fragment fragment = new ListFragment();

        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);

        return fragment;
    }
}