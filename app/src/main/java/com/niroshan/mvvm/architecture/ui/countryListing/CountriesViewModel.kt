package com.niroshan.mvvm.architecture.ui.countryListing

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.niroshan.mvvm.architecture.repository.model.countries.Country
import com.niroshan.mvvm.architecture.repository.repo.countries.CountriesRepository
import javax.inject.Inject

/**
 * Created by Niroshan Rathnayake on 23,November,2019
 * niroshan.sub@gmail.com
 */

/**
 * A container for [Country] related data to show on the UI.
 */
class CountriesViewModel @Inject constructor(
    countriesRepository: CountriesRepository
) : ViewModel() {

    /**
     * Loading news articles from internet and database
     */
    private var countries: LiveData<List<Country>> = countriesRepository.getCountries()


    fun getCountries() = countries

}