package com.niroshan.mvvm.architecture.ui.newsArticles

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.niroshan.mvvm.architecture.repository.api.network.Resource
import com.niroshan.mvvm.architecture.repository.model.news.NewsArticles
import com.niroshan.mvvm.architecture.repository.model.news.NewsSource
import com.niroshan.mvvm.architecture.repository.repo.news.NewsRepositoryUsingBoundResources
import com.niroshan.mvvm.architecture.repository.repo.news.NewsRepositoryUsingExecutor
import javax.inject.Inject

/**
 * Created by Niroshan Rathnayake on 23,November,2019
 * niroshan.sub@gmail.com
 */

/**
 * A container for [NewsArticles] related data to show on the UI.
 */
class NewsArticleViewModel @Inject constructor(
    newsRepository: NewsRepositoryUsingBoundResources,
    userRepository: NewsRepositoryUsingExecutor
) : ViewModel() {

    /**
     * Loading news articles from internet and database
     */
    private var newsArticles: LiveData<Resource<List<NewsArticles>?>> =
        newsRepository.getNewsArticles("us")


    fun getNewsArticles() = newsArticles


    /**
     * Loading news articles from internet only
     */
    private var newsArticlesFromOnlyServer: LiveData<Resource<NewsSource>> =
        newsRepository.getNewsArticlesFromServerOnly("us")

    fun getNewsArticlesFromServer() = newsArticlesFromOnlyServer


    /**
     * Loading news articles from internet and database using Executor
     */
    private var newsArticlesFromServer: LiveData<List<NewsArticles>> =
        userRepository.getNewsArticles()

    fun getNewsArticlesFromServerUsingExecuter() = newsArticlesFromServer
}