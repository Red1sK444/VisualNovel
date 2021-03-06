package com.r3d1r4ph.visualnovel.ui.fragments.input

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.map
import com.r3d1r4ph.visualnovel.domain.usecases.GetScreenByIdUseCase
import com.r3d1r4ph.visualnovel.domain.usecases.GetScreenTypeByIdUseCase
import com.r3d1r4ph.visualnovel.ui.fragments.ScreenViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class InputScreenViewModel @Inject constructor(
    getScreenTypeByIdUseCase: GetScreenTypeByIdUseCase,
    getScreenByIdUseCase: GetScreenByIdUseCase,
    state: SavedStateHandle
) : ScreenViewModel(getScreenTypeByIdUseCase, getScreenByIdUseCase, state) {

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