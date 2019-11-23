package com.niroshan.mvvm.architecture.repository.repo.news

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.niroshan.mvvm.architecture.app.AppExecutors
import com.niroshan.mvvm.architecture.repository.api.ApiServices
import com.niroshan.mvvm.architecture.repository.db.news.NewsArticlesDao
import com.niroshan.mvvm.architecture.repository.model.news.NewsArticles
import com.niroshan.mvvm.architecture.repository.model.news.NewsSource
import com.niroshan.mvvm.architecture.utils.ConnectivityUtil
import javax.inject.Singleton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by Niroshan Rathnayake on 23,November,2019
 * niroshan.sub@gmail.com
 */

@Singleton
class NewsRepositoryUsingExecutor @Inject
constructor(
    private val context: Context,
    private val apiServices: ApiServices,
    private val newsDao: NewsArticlesDao,
    private val appExecutors: AppExecutors = AppExecutors()
) {
    /**
     *
     */
    fun getNewsArticles(): LiveData<List<NewsArticles>> {
        if (ConnectivityUtil.isConnected(context)) {

            getNewsArticlesFromServer() // try to refresh data if possible from Github Api
        }

        return newsDao.getNewsArticles() // return a LiveData directly from the database.
    }

    // ---
    private fun getNewsArticlesFromServer() {
        appExecutors.networkIO().execute {
            // If user have to be updated
            apiServices.getNewsSourceUsingCall().enqueue(object : Callback<NewsSource> {
                override fun onResponse(call: Call<NewsSource>, response: Response<NewsSource>) {
                    Log.e("TAG", "DATA REFRESHED FROM NETWORK")
                    appExecutors.diskIO().execute {
                        newsDao.insertArticles(response.body()?.articles!!)
                    }
                }

                override fun onFailure(call: Call<NewsSource>, t: Throwable) {
                    Log.e("TAG", "DATA REFRESHED FROM NETWORK")
                }
            })
        }
    }

}
