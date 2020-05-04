package com.example.movie.database;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface TiketDAO {

    @Insert
    long insertTiket(TiketModel tiketModel);

}
