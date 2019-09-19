package com.example.search.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.search.db.model.Movie;

import java.util.List;
import java.util.Observable;

import io.reactivex.Flowable;

@Dao
public interface DaoAccess {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Movie movie);

    @Query("SELECT * FROM Movie")
    Flowable<List<Movie>> getList();

    @Delete
    void delete(Movie note);
}
