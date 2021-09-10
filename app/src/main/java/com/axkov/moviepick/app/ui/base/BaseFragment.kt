package com.axkov.moviepick.app.ui.base

import android.view.View
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

abstract class BaseFragment(layoutId: Int): Fragment(layoutId) {
    protected lateinit var progressBar: ProgressBar

    lateinit var viewModelFactory: ViewModelProvider.Factory

    private fun showProgressBar(isLoading: Boolean) {
        if (isLoading) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }
}