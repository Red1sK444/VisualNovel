package com.r3d1r4ph.visualnovel.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.r3d1r4ph.visualnovel.BuildConfig
import com.r3d1r4ph.visualnovel.R
import com.r3d1r4ph.visualnovel.databinding.ActivityMainBinding
import com.r3d1r4ph.visualnovel.di.MainViewModelAssistedFactory
import com.r3d1r4ph.visualnovel.ui.utils.Utils
import com.r3d1r4ph.visualnovel.ui.viewmodel.MainViewModel
import com.r3d1r4ph.visualnovel.ui.viewmodel.MainViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var mainViewModelAssistedFactory: MainViewModelAssistedFactory

    private val viewBinding by viewBinding(ActivityMainBinding::bind, R.id.rootLayout)
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
        initObservers()
    }

    private fun initObservers() {
        viewModel.exceptionId.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
        viewModel.screensLoaded.observe(this) {
            if (it) {
                viewBinding.fragmentContainerView.findNavController()
                    .setGraph(R.navigation.nav_graph)
            }
        }
    }
}