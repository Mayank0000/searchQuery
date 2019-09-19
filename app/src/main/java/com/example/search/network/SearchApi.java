package com.example.search.network;

import com.example.search.model.Details;
import com.example.search.model.Search;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchApi {

    @GET("/")
    Flowable<Search> getSearchList(@Query("apikey") String apiKey,
                                   @Query("s") String searchQuery,
                                   @Query("page") int page);

    @GET("/")
    Flowable<Details> getMovieDetail(@Query("apikey") String apiKey,
                                       @Query("i") String imDbId);

}
