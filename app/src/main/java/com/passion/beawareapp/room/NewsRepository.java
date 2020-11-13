package com.passion.beawareapp.room;

import android.app.Application;
import android.app.AsyncNotedAppOp;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NewsRepository {

    private NewsDao newsDao;
    private LiveData<List<News>> allNews;

    public NewsRepository(Application application){
        NewsDatabase newsDatabase = NewsDatabase.getDatabase(application);
        newsDao = newsDatabase.newsDao();
        allNews = newsDao.getAllNews();
    }

    public LiveData<List<News>> getAllNews(){
        return allNews;
    }

    public void insertNews(News n ){
        new AsyncInsert(newsDao).execute(n);
    }

    public void deleteAll(){
        new AsyncDelete(newsDao).execute();
    }


    private static class AsyncInsert extends AsyncTask<News, Void, Void > {

        NewsDao newsDao;

        private AsyncInsert(NewsDao newsDao ){
            this.newsDao = newsDao;
        }

        @Override
        protected Void doInBackground(News... news) {
            if( news[0] != null )
                newsDao.insertNews(news[0]);

            return null;
        }
    }

    private static class AsyncDelete extends AsyncTask<Void, Void, Void > {

        NewsDao newsDao;

        private AsyncDelete(NewsDao newsDao ){
            this.newsDao = newsDao;
        }


        @Override
        protected Void doInBackground(Void... voids) {
            newsDao.deleteAll();

            //TODO: reset the primary key count

            return null;
        }
    }
}
