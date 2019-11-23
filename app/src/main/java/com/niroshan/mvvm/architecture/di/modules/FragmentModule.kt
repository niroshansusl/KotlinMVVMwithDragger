package com.niroshan.mvvm.architecture.di.modules

import com.niroshan.mvvm.architecture.ui.countryListing.CountryListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentModule {

    /**
     * Injecting Fragments
     */
    @ContributesAndroidInjector
    internal abstract fun contributeCountryListFragment(): CountryListFragment
}