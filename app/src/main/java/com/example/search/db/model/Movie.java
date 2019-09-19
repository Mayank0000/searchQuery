package com.example.search.db.model;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.io.Serializable;


@Entity
public class Movie implements Serializable {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "imdbId")
    public String imDbID;

    @ColumnInfo(name = "imageUrl")
    public String imageUrl;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "year")
    public String year;

}
