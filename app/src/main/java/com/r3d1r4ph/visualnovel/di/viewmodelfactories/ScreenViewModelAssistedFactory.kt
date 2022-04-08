package com.r3d1r4ph.visualnovel.di.viewmodelfactories

import com.r3d1r4ph.visualnovel.ui.fragments.viewmodel.ScreenViewModel
import dagger.assisted.AssistedFactory

@AssistedFactory
interface ScreenViewModelAssistedFactory {
    fun create(
        screenId: Int
    ): ScreenViewModel
}