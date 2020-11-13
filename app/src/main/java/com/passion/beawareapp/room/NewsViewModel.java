package com.passion.beawareapp.room;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NewsViewModel extends AndroidViewModel {

    NewsRepository newsRepository;
    LiveData<List<News>> allNews;

    public NewsViewModel(@NonNull Application application) {
        super(application);
        newsRepository = new NewsRepository(application);
        allNews = newsRepository.getAllNews();
    }

    public LiveData<List<News>> getAllNews(){
        return allNews;
    }

    public void insertNews(News n){
        newsRepository.insertNews(n);
    }

    public void deleteAll(){
        newsRepository.deleteAll();
    }
}
