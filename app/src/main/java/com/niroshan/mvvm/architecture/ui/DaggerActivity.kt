package com.niroshan.mvvm.architecture.ui

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

/**
 * Created by Niroshan Rathnayake on 23,November,2019
 * niroshan.sub@gmail.com
 */

// Referring this class as BaseActivity

typealias BaseActivity = DaggerActivity

/**
 * Activity providing Dagger support and [ViewModel] support
 */
abstract class DaggerActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun supportFragmentInjector() = dispatchingAndroidInjector

}
