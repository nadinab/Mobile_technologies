package by.brstu.redlabrat.testlistapp;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import by.brstu.redlabrat.testlistapp.api.OmdbSearchResultMovie;
import by.brstu.redlabrat.testlistapp.db.MovieDao;

import com.bumptech.glide.Glide;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.MaybeOnSubscribe;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.core.SingleOnSubscribe;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.MyTestItemViewHolder> {


    private List<OmdbSearchResultMovie> listOfItems;
    private MovieDao dbDao;
    private MyItemClickListener onItemClickListener;

    MyListAdapter(List<OmdbSearchResultMovie> listOfItems, MovieDao dbDao, MyItemClickListener onItemClickListener) {
        this.listOfItems = listOfItems;
        this.dbDao = dbDao;
        this.onItemClickListener = onItemClickListener;
    }

    interface MyItemClickListener {
        void onMyItemClick(OmdbSearchResultMovie selectedStr);

        void addToFavorites(OmdbSearchResultMovie selectedMovie);
    }

    void setNewListOfItems(List<OmdbSearchResultMovie> newList) {
        listOfItems = newList;
        notifyDataSetChanged();
        Log.e("HAHAHA", newList.get(0).title);
    }

    public static class MyTestItemViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;
        ImageButton button;

        MyTestItemViewHolder(View view) {
            super(view);

            imageView = itemView.findViewById(R.id.listItemImageView);
            textView = itemView.findViewById(R.id.listItemTextView);
            button = itemView.findViewById(R.id.favoritesImageButton);

        }
    }

    @NonNull
    @Override
    public MyTestItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new MyTestItemViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return listOfItems.size();
    }

    @Override
    public void onBindViewHolder(@NonNull MyTestItemViewHolder holder, int position) {
        Log.e("GOGOGO", "A");
        OmdbSearchResultMovie movie = listOfItems.get(holder.getAdapterPosition());
        holder.textView.setText(movie.title);

        Glide.with(holder.itemView.getContext())
                .load(movie.posterUrl)
                .into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onMyItemClick(movie);
            }
        });

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.addToFavorites(movie);
                notifyItemChanged(holder.getAdapterPosition());
            }
        });

        findMovieById(movie.imdbId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Boolean>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull Boolean aBoolean) {
                        if (aBoolean) {
                            holder.button.setImageResource(R.drawable.heart_red);
                        }
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                    }
                });

    }


    private Single<Boolean> findMovieById(String id) {
        return Single.create((SingleOnSubscribe<Boolean>) emitter -> {
            OmdbSearchResultMovie movie = dbDao.getMovieById(id);

            if (movie != null) {
                emitter.onSuccess(true);
            } else {
                emitter.onSuccess(false);
            }
        });

    }
}