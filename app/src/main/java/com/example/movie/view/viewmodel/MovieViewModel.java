package com.example.movie.view.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.movie.model.movie.MovieResponse;
import com.example.movie.model.movie.MovieResultsItem;
import com.example.movie.service.ApiMain;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieViewModel extends ViewModel {
    private ApiMain apiMain;

    private MutableLiveData<ArrayList<MovieResultsItem>> listMovie = new MutableLiveData<>();

    public void  setMovie(){
        if (this.apiMain == null){
            apiMain = new ApiMain();
        }
        apiMain.getApiMovie().getMovie().enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                MovieResponse responseMovie = response.body();
                if (responseMovie != null && responseMovie.getResults() != null){
                    ArrayList<MovieResultsItem> movieItems = responseMovie.getResults();
                    listMovie.postValue(movieItems);
                    Log.e("MovieViewModel", "onSuccess" );
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.e("MovieViewModel", "onFailure : "+t.getMessage() );

            }
        });
    }
    public LiveData<ArrayList<MovieResultsItem>> getMovie(){
        return  listMovie;
    }
}
