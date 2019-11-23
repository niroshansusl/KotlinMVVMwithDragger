package com.niroshan.mvvm.architecture.repository.repo.news

import android.content.Context
import androidx.lifecycle.LiveData
import com.niroshan.mvvm.architecture.app.AppExecutors
import com.niroshan.mvvm.architecture.repository.api.ApiServices
import com.niroshan.mvvm.architecture.repository.api.network.NetworkAndDBBoundResource
import com.niroshan.mvvm.architecture.repository.api.network.NetworkResource
import com.niroshan.mvvm.architecture.repository.api.network.Resource
import com.niroshan.mvvm.architecture.repository.db.news.NewsArticlesDao
import com.niroshan.mvvm.architecture.repository.model.news.NewsArticles
import com.niroshan.mvvm.architecture.repository.model.news.NewsSource
import com.niroshan.mvvm.architecture.BuildConfig
import com.niroshan.mvvm.architecture.utils.ConnectivityUtil
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Niroshan Rathnayake on 23,November,2019
 * niroshan.sub@gmail.com
 */

/**
 * Repository abstracts the logic of fetching the data and persisting it for
 * offline. They are the data source as the single source of truth.
 *
 */

@Singleton
class NewsRepositoryUsingBoundResources @Inject constructor(
    private val newsDao: NewsArticlesDao,
    private val apiServices: ApiServices, private val context: Context,
    private val appExecutors: AppExecutors = AppExecutors()
) {

    /**
     * Fetch the news articles from database if exist else fetch from web
     * and persist them in the database
     */
    fun getNewsArticles(countryShortKey: String): LiveData<Resource<List<NewsArticles>?>> {

        val data = HashMap<String, String>()
        data.put("country", countryShortKey)
        data.put("apiKey", BuildConfig.NEWS_API_KEY)

        return object : NetworkAndDBBoundResource<List<NewsArticles>, NewsSource>(appExecutors) {
            override fun saveCallResult(item: NewsSource) {
                if (!item.articles.isEmpty()) {
                    newsDao.deleteAllArticles()
                    newsDao.insertArticles(item.articles)
                }
            }

            override fun shouldFetch(data: List<NewsArticles>?) =
                (ConnectivityUtil.isConnected(context))

            override fun loadFromDb() = newsDao.getNewsArticles()

            override fun createCall() =
                apiServices.getNewsSource(data)

        }.asLiveData()
    }

    /**
     * Fetch the news articles from database if exist else fetch from web
     * and persist them in the database
     * LiveData<Resource<NewsSource>>
     */
    fun getNewsArticlesFromServerOnly(countryShortKey: String):
            LiveData<Resource<NewsSource>> {

        val data = HashMap<String, String>()
        data.put("country", countryShortKey)
        data.put("apiKey", BuildConfig.NEWS_API_KEY)

        return object : NetworkResource<NewsSource>() {
            override fun createCall(): LiveData<Resource<NewsSource>> {
                return apiServices.getNewsSource(data)
            }

        }.asLiveData()
    }

}