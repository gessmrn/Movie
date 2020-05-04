package com.example.movie.service;

import com.example.movie.model.movie.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieRepository {
    @GET("3/discover/movie?api_key=18c77ebd8f9e2ab1dc05064b2569b94c")
    Call<MovieResponse> getMovie();
}
