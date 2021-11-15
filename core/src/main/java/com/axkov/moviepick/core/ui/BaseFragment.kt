package com.axkov.moviepick.core.ui

import android.view.View
import android.widget.ProgressBar
import androidx.fragment.app.Fragment

abstract class BaseFragment(layoutId: Int): Fragment(layoutId) {
    protected lateinit var progressBar: ProgressBar

    private fun showProgressBar(isLoading: Boolean) {
        if (isLoading) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }
}