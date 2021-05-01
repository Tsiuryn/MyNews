package com.ts.alex.mynews.ui

import androidx.appcompat.app.AppCompatActivity
import com.ts.alex.mynews.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(R.layout.activity_main){

    private val viewModel by viewModel<MainViewModel>()

    override fun onResume() {
        super.onResume()
        viewModel.cancelJob()
    }

    override fun onPause() {
        super.onPause()
        viewModel.startJob()
    }
}