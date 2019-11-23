package com.niroshan.mvvm.architecture.utils

import android.content.Context
import android.util.DisplayMetrics

/**
 * Created by Niroshan Rathnayake on 23,November,2019
 * niroshan.sub@gmail.com
 */

object PixelUtil {

    fun dpToPx(context: Context, dp: Int): Int {
        return Math.round(dp * getPixelScaleFactor(context))
    }

    fun pxToDp(context: Context, px: Int): Int {
        return Math.round(px / getPixelScaleFactor(context))
    }

    private fun getPixelScaleFactor(context: Context): Float {
        val displayMetrics = context.resources.displayMetrics
        return displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT
    }
}