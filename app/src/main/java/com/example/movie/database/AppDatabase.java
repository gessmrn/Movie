package com.example.movie.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {TiketModel.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract TiketDAO tiketDAO();
    private static AppDatabase appDatabase;

    public static AppDatabase initDatabase(Context context){
        if (appDatabase == null){
            appDatabase = Room.databaseBuilder(
                    context,
                    AppDatabase.class,
                    "tiket"
            ).allowMainThreadQueries().build();
        }
        return appDatabase;
    }
}
