package com.r3d1r4ph.visualnovel.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.r3d1r4ph.visualnovel.BuildConfig
import com.r3d1r4ph.visualnovel.R
import com.r3d1r4ph.visualnovel.di.viewmodelfactories.MainViewModelAssistedFactory
import com.r3d1r4ph.visualnovel.ui.utils.Utils
import com.r3d1r4ph.visualnovel.ui.viewmodel.MainViewModel
import com.r3d1r4ph.visualnovel.ui.viewmodel.MainViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var mainViewModelAssistedFactory: MainViewModelAssistedFactory

    private val viewModel by viewModels<MainViewModel> {
        MainViewModelFactory(
            assistedFactory = mainViewModelAssistedFactory,
            screensJsonString = Utils.getJsonDataFromAsset(
                this,
                BuildConfig.SCRIPT_FILE_NAME
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.navigationBarColor = ContextCompat.getColor(this, R.color.black)
        initObserver()
    }

    private fun initObserver() {
        viewModel.exceptionId.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }
}