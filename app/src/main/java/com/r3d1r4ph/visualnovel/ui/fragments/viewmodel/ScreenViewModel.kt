package com.r3d1r4ph.visualnovel.ui.fragments.viewmodel

import androidx.lifecycle.*
import com.r3d1r4ph.visualnovel.R
import com.r3d1r4ph.visualnovel.common.exceptions.LoadScreensException
import com.r3d1r4ph.visualnovel.domain.models.Screen
import com.r3d1r4ph.visualnovel.domain.usecases.GetScreenByIdUseCase
import com.r3d1r4ph.visualnovel.domain.usecases.GetScreenTypeByIdUseCase
import com.r3d1r4ph.visualnovel.ui.fragments.OpenScreenArgs
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch
import timber.log.Timber

open class ScreenViewModel @AssistedInject constructor(
    private val getScreenTypeByIdUseCase: GetScreenTypeByIdUseCase,
    private val getScreenByIdUseCase: GetScreenByIdUseCase,
    @Assisted screenId: Int
) : ViewModel() {

    private val _screen = MutableLiveData<Screen>()
    val screen: LiveData<Screen>
        get() = _screen.map { it }

    private val _openNextScreen = MutableLiveData<OpenScreenArgs>()
    val openNextScreen: LiveData<OpenScreenArgs>
        get() = _openNextScreen.map { it }

    private val _exceptionId = MutableLiveData<Int>()
    val exceptionId: LiveData<Int>
        get() = _exceptionId.map { it }

    init {
        Timber.i("ViewModel screen ${Thread.currentThread().name}")
        getScreenById(screenId)
    }

    fun openNextScreen(screenId: Int, name: String? = null) {
        viewModelScope.launch {
            val result = getScreenTypeByIdUseCase.invoke(screenId)
            if (result.isSuccess) {
                _openNextScreen.postValue(
                    OpenScreenArgs(
                        screenId = screenId,
                        name = name,
                        screenType = result.getOrThrow()
                    )
                )
            } else {
                handleException(result.exceptionOrNull())
            }
        }
    }

    private fun getScreenById(screenId: Int) {
        viewModelScope.launch {
            Timber.i("scope ${Thread.currentThread().name}")
            val result = getScreenByIdUseCase.invoke(screenId)
            if (result.isSuccess) {
                result.getOrNull()?.let {
                    _screen.value = it
                }
            } else {
                handleException(result.exceptionOrNull())
            }
        }
    }

    private fun handleException(exception: Throwable?) {
        when (exception) {
            is LoadScreensException -> _exceptionId.postValue(R.string.load_screen_exception)
            else -> _exceptionId.postValue(R.string.unknown_exception)
        }
    }
}