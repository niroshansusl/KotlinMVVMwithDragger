package com.niroshan.mvvm.architecture.repository.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.niroshan.mvvm.architecture.repository.db.countries.CountriesDao
import com.niroshan.mvvm.architecture.repository.db.news.NewsArticlesDao
import com.niroshan.mvvm.architecture.repository.model.countries.Country
import com.niroshan.mvvm.architecture.repository.model.news.NewsArticles

/**
 * Created by Niroshan Rathnayake on 23,November,2019
 * niroshan.sub@gmail.com
 */

/**
 * App Database
 * Define all entities and access doa's here/ Each entity is a table.
 */
@Database(entities = [NewsArticles::class, Country::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    /**
     * Get DAO's
     */

    abstract fun newsArticlesDao(): NewsArticlesDao

    abstract fun countriesDao(): CountriesDao
}