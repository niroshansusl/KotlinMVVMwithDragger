package com.niroshan.mvvm.architecture.ui.countryListing

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.niroshan.mvvm.architecture.repository.model.countries.Country

import com.kotlin.mvvm.utils.extensions.observe
import com.niroshan.mvvm.architecture.R
import com.niroshan.mvvm.architecture.di.base.Injectable
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_country_list.*
import javax.inject.Inject

/**
 * Created by Niroshan Rathnayake on 23,November,2019
 * niroshan.sub@gmail.com
 */

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [CountryListFragment.OnCountriesListClickListener] interface.
 */
class CountryListFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory


    private var columnCount = 1

    private var listener: OnCountriesListClickListener? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_country_list, container, false)

        val countriesViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(CountriesViewModel::class.java)

        val listOfCountries = ArrayList<Country>()
        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {

                    columnCount <= 1 -> LinearLayoutManager(context)

                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = CountryListRecyclerViewAdapter(listOfCountries, listener)
            }
        }

        countriesViewModel.getCountries().observe(this) {
            // You'l get loist of countries here
            listOfCountries.clear()
            listOfCountries.addAll(it)
            recyclerview_countries.adapter?.notifyDataSetChanged()
        }


        return view
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this) // Providing the dependency
        super.onAttach(context)
        if (context is OnCountriesListClickListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnCountriesListClickListener {
        fun onCountriesListClick(country: Country)
    }

    companion object {
        const val ARG_COLUMN_COUNT = "column-count"

        @JvmStatic
        fun newInstance(columnCount: Int) =
            CountryListFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}
