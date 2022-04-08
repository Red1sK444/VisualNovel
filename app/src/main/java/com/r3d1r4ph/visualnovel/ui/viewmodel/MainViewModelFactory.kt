package com.r3d1r4ph.visualnovel.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.r3d1r4ph.visualnovel.di.MainViewModelAssistedFactory

class MainViewModelFactory(
    private val assistedFactory: MainViewModelAssistedFactory,
    private val screensJsonString: String
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return assistedFactory.create(screensJsonString) as T
    }
}