package com.axkov.moviepick.app.ui.main

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.axkov.moviepick.app.R
import com.axkov.moviepick.app.ui.base.BaseFragment

class MainScreenFragment: BaseFragment(R.layout.fragment_main_screen) {
    private lateinit var viewModel: ViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainScreenViewModel::class.java)
    }
}