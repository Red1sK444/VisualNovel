package com.r3d1r4ph.visualnovel.ui.fragments

import androidx.lifecycle.*
import com.r3d1r4ph.visualnovel.R
import com.r3d1r4ph.visualnovel.common.exceptions.LoadScreensException
import com.r3d1r4ph.visualnovel.domain.models.Screen
import com.r3d1r4ph.visualnovel.domain.usecases.GetScreenByIdUseCase
import com.r3d1r4ph.visualnovel.domain.usecases.GetScreenTypeByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
open class ScreenViewModel @Inject constructor(
    private val getScreenTypeByIdUseCase: GetScreenTypeByIdUseCase,
    private val getScreenByIdUseCase: GetScreenByIdUseCase,
    state: SavedStateHandle
) : ViewModel() {

    private companion object {
        const val SCREEN_ID = "screenId"
    }

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
        //Timber.i("ViewModel screen ${Thread.currentThread().name}")

        val screenId = state.get<Int>(SCREEN_ID)
        if (screenId != null) {
            getScreenById(screenId)
        } else {
            _exceptionId.value = R.string.unknown_exception
        }
    }

    fun openNextScreen(screenId: Int, name: String? = null) {
        viewModelScope.launch {
            val result = getScreenTypeByIdUseCase.invoke(screenId)
            if (result.isSuccess) {
                _openNextScreen.value =
                    OpenScreenArgs(
                        screenId = screenId,
                        name = name,
                        screenType = result.getOrThrow()
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
            is LoadScreensException -> _exceptionId.value = R.string.load_screen_exception
            else -> _exceptionId.value = R.string.unknown_exception
        }
    }
}