package com.passion.beawareapp.room;

import android.app.SearchManager;
import android.icu.text.Replaceable;
import android.provider.ContactsContract;

import androidx.lifecycle.LiveData;
import androidx.room.ColumnInfo;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.Collection;
import java.util.List;

@Dao
public interface NewsDao{

    @Query("Select * from news_table")
    LiveData<List<News>> getAllNews();

    @Insert(onConflict = OnConflictStrategy.REPLACE )
    void insertNews(News n);

    @Query("DELETE FROM news_table")
    void deleteAll();

}
