package com.r3d1r4ph.visualnovel.di.viewmodelfactories

import com.r3d1r4ph.visualnovel.ui.fragments.input.InputScreenViewModel
import dagger.assisted.AssistedFactory

@AssistedFactory
interface InputScreenViewModelAssistedFactory : ScreenViewModelAssistedFactory {
    override fun create(
        screensJsonString: String,
        screenId: Int
    ): InputScreenViewModel
}