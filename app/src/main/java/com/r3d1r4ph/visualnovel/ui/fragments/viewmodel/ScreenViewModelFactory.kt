package com.r3d1r4ph.visualnovel.ui.fragments.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.r3d1r4ph.visualnovel.di.viewmodelfactories.ScreenViewModelAssistedFactory

class ScreenViewModelFactory(
    private val assistedFactory: ScreenViewModelAssistedFactory,
    private val screensJsonString: String
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return assistedFactory.create(screensJsonString) as T
    }
}