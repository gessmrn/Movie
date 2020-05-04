package com.example.movie.view.fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.movie.R;
import com.example.movie.adapter.MovieAdapter;
import com.example.movie.model.movie.MovieResultsItem;
import com.example.movie.view.viewmodel.MovieViewModel;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {

    private MovieAdapter movieAdapter;
    private RecyclerView rvMovie;
    private MovieViewModel movieViewModel;

    public MovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        movieAdapter = new MovieAdapter(getContext());
        movieAdapter.notifyDataSetChanged();

        rvMovie = view.findViewById(R.id.recyclerview_movie);
        rvMovie.setLayoutManager(new GridLayoutManager(getContext(),2));

        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        movieViewModel.setMovie();
        movieViewModel.getMovie().observe(this,getMovie);

        rvMovie.setAdapter(movieAdapter);

    }

    private Observer<ArrayList<MovieResultsItem>> getMovie = new Observer<ArrayList<MovieResultsItem>>() {
        @Override
        public void onChanged(ArrayList<MovieResultsItem> movieResultsItems) {
            if (movieResultsItems != null){
                movieAdapter.setData(movieResultsItems);
            }
        }
    };
}
