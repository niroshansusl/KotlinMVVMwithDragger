package com.niroshan.mvvm.architecture.repository.api

import androidx.lifecycle.LiveData
import com.niroshan.mvvm.architecture.repository.api.network.Resource
import com.niroshan.mvvm.architecture.repository.model.news.NewsSource
import com.niroshan.mvvm.architecture.BuildConfig
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap


/**
 * Created by Niroshan Rathnayake on 23,November,2019
 * niroshan.sub@gmail.com
 */

/**
 * Api services to communicate with server
 *
 */
interface ApiServices {

    /**
     * Fetch news articles from Google news using GET API Call on given Url
     * Url would be something like this top-headlines?country=my&apiKey=XYZ
     */
    @GET("top-headlines")
    fun getNewsSource(@QueryMap options: Map<String, String>): LiveData<Resource<NewsSource>>


    /**
     * Fetch news articles from Google news using GET API Call on given Url
     * Using Call, By Retrofit
     */
    @GET("top-headlines?sources=google-news&apiKey=" + BuildConfig.NEWS_API_KEY)
    fun getNewsSourceUsingCall(): Call<NewsSource>

}
