package com.r3d1r4ph.visualnovel.ui.fragments.input

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.r3d1r4ph.visualnovel.data.ScreenDataSource
import com.r3d1r4ph.visualnovel.ui.fragments.ScreenViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class InputScreenViewModel @Inject constructor(
    screenDataSource: ScreenDataSource
) : ScreenViewModel(screenDataSource) {

    private val _validation = MutableLiveData<Result<String>>()
    val validation: LiveData<Result<String>>
        get() = _validation

    fun validateName(name: String) {
        _validation.value = if (name.isNotEmpty()) {
            Result.success(name)
        } else {
            Result.failure(Exception())
        }
    }
}