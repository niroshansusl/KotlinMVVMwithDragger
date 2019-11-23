package com.niroshan.mvvm.architecture.repository.model.news

import com.google.gson.annotations.SerializedName
import com.niroshan.mvvm.architecture.repository.model.news.NewsArticles


/**
 * Created by Niroshan Rathnayake on 23,November,2019
 * niroshan.sub@gmail.com
 */

/**
 * News source model describing the source details
 * and the articles under it.
 */
data class NewsSource(
    @SerializedName("status") var status: String = "",
    @SerializedName("source") var source: String = "",
    @SerializedName("sortBy") var sortBy: String = "",
    @SerializedName("articles") var articles: List<NewsArticles> = emptyList()
)