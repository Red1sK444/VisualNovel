package com.r3d1r4ph.visualnovel.ui.fragments

import androidx.lifecycle.*
import com.r3d1r4ph.visualnovel.common.exceptions.UnknownException
import com.r3d1r4ph.visualnovel.data.ScreenRepository
import com.r3d1r4ph.visualnovel.domain.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class ScreenViewModel @Inject constructor(
    private val screenRepository: ScreenRepository
) : ViewModel() {

    private val _screen = MutableLiveData<Screen>()
    val screen: LiveData<Screen>
        get() = _screen.map { it }

    private val _openNextScreen = MutableLiveData<OpenScreenArgs>()
    val openNextScreen: LiveData<OpenScreenArgs>
        get() = _openNextScreen.map { it }

    private val _exception = MutableLiveData<Exception>()
    val exception: LiveData<Exception>
        get() = _exception.map { it }

    fun openNextScreen(screenId: Int, name: String? = null) {
        viewModelScope.launch {
            val result = screenRepository.getScreenTypeById(screenId)
            if (result.isSuccess) {
                try {
                    _openNextScreen.postValue(
                        OpenScreenArgs(
                            screenId = screenId,
                            name = name,
                            screenType = result.getOrThrow()
                        )
                    )
                } catch (t: Throwable) {
                    _exception.postValue(
                        UnknownException()
                    )
                }
            } else {
                _exception.postValue(
                    (result.exceptionOrNull() ?: UnknownException()) as Exception
                )
            }
        }
    }

    fun getScreenById(screenId: Int) {
        viewModelScope.launch {
            val result = screenRepository.getScreenById(screenId)
            if (result.isSuccess) {
                result.getOrNull()?.let {
                    _screen.value = it
                }
            } else {
                _exception.postValue(
                    (result.exceptionOrNull() ?: UnknownException()) as Exception
                )
            }
        }
    }
}