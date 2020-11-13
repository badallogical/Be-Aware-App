package com.passion.beawareapp.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {News.class}, version = 1, exportSchema = false)
public abstract class NewsDatabase extends RoomDatabase {

    private static volatile NewsDatabase instance;
    public abstract NewsDao newsDao();

    static public NewsDatabase getDatabase( Context context ){
        if( instance == null ){
            synchronized (NewsDatabase.class){
                instance = Room.databaseBuilder( context.getApplicationContext(), NewsDatabase.class, "news_db").build();
            }
        }

        return instance;
    }

}
