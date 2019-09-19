package com.example.search.db;
import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.search.db.model.Movie;


@Database(entities = {Movie.class}, version = 1, exportSchema = false)
public abstract class BookMarkDB extends RoomDatabase {

    public abstract DaoAccess daoAccess();
}
