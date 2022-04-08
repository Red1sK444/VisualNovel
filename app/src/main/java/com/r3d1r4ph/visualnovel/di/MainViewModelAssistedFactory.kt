package com.r3d1r4ph.visualnovel.di

import com.r3d1r4ph.visualnovel.ui.viewmodel.MainViewModel
import dagger.assisted.AssistedFactory

@AssistedFactory
interface MainViewModelAssistedFactory {
    fun create(
        screensJsonString: String
    ): MainViewModel
}