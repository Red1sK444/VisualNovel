package com.r3d1r4ph.visualnovel

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity: AppCompatActivity() {

    @Inject
    lateinit var screenDataSource: ScreenDataSource

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.navigationBarColor = ContextCompat.getColor(this, R.color.black)

        val screen = screenDataSource.getScreenById(1)
        when (screen) {
            is ResultWrapper.Success -> Log.i("dataaa", "${screen.value}")
            is ResultWrapper.Failure -> Log.i("dataaa", "err")
        }

    }
}