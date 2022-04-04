package com.r3d1r4ph.visualnovel.ui.fragments.viewmodel

import androidx.lifecycle.*
import com.r3d1r4ph.visualnovel.common.exceptions.UnknownException
import com.r3d1r4ph.visualnovel.domain.ScreenRepository
import com.r3d1r4ph.visualnovel.domain.Screen
import com.r3d1r4ph.visualnovel.ui.fragments.OpenScreenArgs
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch

open class ScreenViewModel @AssistedInject constructor(
    private val screenRepository: ScreenRepository,
    @Assisted screensJsonString: String,
    @Assisted screenId: Int
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

    init {
        viewModelScope.launch {
            if (!screenRepository.isScreensLoaded()) {
                screenRepository.loadScreens(screensJsonString)
            }
            getScreenById(screenId)
        }
    }

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