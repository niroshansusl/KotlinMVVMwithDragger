package com.niroshan.mvvm.architecture.ui.countryListing

import android.content.Intent
import android.os.Bundle
import com.niroshan.mvvm.architecture.R
import com.niroshan.mvvm.architecture.repository.model.countries.Country
import com.niroshan.mvvm.architecture.ui.BaseActivity
import com.niroshan.mvvm.architecture.ui.newsArticles.NewsArticlesActivity
import com.niroshan.mvvm.architecture.ui.newsArticles.NewsArticlesActivity.Companion.KEY_COUNTRY_SHORT_KEY

/**
 * Created by Niroshan Rathnayake on 23,November,2019
 * niroshan.sub@gmail.com
 */

/**
 * Countries Listing Activity.
 */
class CountryListingActivity() : BaseActivity(),
    CountryListFragment.OnCountriesListClickListener {

    /**
     * On Create Of Activity
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_countires)

        // 1
        if (savedInstanceState == null) {
            // 2
            supportFragmentManager
                // 3
                .beginTransaction()
                // 4
                .add(R.id.fragment_container, CountryListFragment.newInstance(1))
                // 5
                .commit()
        }

    }

    override fun onCountriesListClick(country: Country) {
        val intent = Intent(this, NewsArticlesActivity::class.java)
        intent.putExtra(KEY_COUNTRY_SHORT_KEY, country.countryKey)
        startActivity(intent)
    }
}
