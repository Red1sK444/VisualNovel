package com.r3d1r4ph.visualnovel.ui.fragments.input

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.r3d1r4ph.visualnovel.domain.ScreenRepository
import com.r3d1r4ph.visualnovel.ui.fragments.viewmodel.ScreenViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

class InputScreenViewModel @AssistedInject constructor(
    screenRepository: ScreenRepository,
    @Assisted screensJsonString: String
) : ScreenViewModel(screenRepository, screensJsonString) {

    private val _validation = MutableLiveData<Result<String>>()
    val validation: LiveData<Result<String>>
        get() = _validation.map { it }

    fun validateName(name: String) {
        _validation.value = if (name.isNotEmpty()) {
            Result.success(name)
        } else {
            Result.failure(Exception())
        }
    }
}