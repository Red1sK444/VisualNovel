package com.r3d1r4ph.visualnovel.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.r3d1r4ph.visualnovel.R
import com.r3d1r4ph.visualnovel.data.ScreenDataSource
import com.r3d1r4ph.visualnovel.utils.ResultWrapper
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.navigationBarColor = ContextCompat.getColor(this, R.color.black)
    }
}