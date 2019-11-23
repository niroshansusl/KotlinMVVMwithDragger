package com.niroshan.mvvm.architecture.di.modules

import com.niroshan.mvvm.architecture.ui.countryListing.CountryListingActivity
import com.niroshan.mvvm.architecture.ui.newsArticles.NewsArticlesActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Niroshan Rathnayake on 23,November,2019
 * niroshan.sub@gmail.com
 */

/**
 * All your Activities participating in DI would be listed here.
 */
@Module(includes = [FragmentModule::class]) // Including Fragment Module Available For Activities
abstract class ActivityModule {

    /**
     * Marking Activities to be available to contributes for Android Injector
     */
    @ContributesAndroidInjector
    abstract fun contributeNewsArticlesActivity(): NewsArticlesActivity

    @ContributesAndroidInjector
    abstract fun contributeCountryListingActivity(): CountryListingActivity
}
