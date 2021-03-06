package com.r3d1r4ph.visualnovel.ui.viewmodel

import androidx.lifecycle.*
import com.r3d1r4ph.visualnovel.R
import com.r3d1r4ph.visualnovel.common.exceptions.LoadScreensException
import com.r3d1r4ph.visualnovel.domain.usecases.LoadScreensUseCase
import com.r3d1r4ph.visualnovel.ui.utils.SingleLiveEvent
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch
import timber.log.Timber

class MainViewModel @AssistedInject constructor(
    loadScreensUseCase: LoadScreensUseCase,
    @Assisted screensJsonString: String
) : ViewModel() {

    private val _exceptionId = MutableLiveData<Int>()
    val exceptionId: LiveData<Int>
        get() = _exceptionId.map { it }

    private val _screensLoaded = SingleLiveEvent<Boolean>().also { it.setValue(false) }
    val screensLoaded: LiveData<Boolean>
        get() = _screensLoaded.map { it }

    init {
        Timber.i("begin ${Thread.currentThread().name}")
        viewModelScope.launch {
            val result = loadScreensUseCase.invoke(screensJsonString)
            if (result.isFailure) {
                when (result.exceptionOrNull()) {
                    is LoadScreensException -> _exceptionId.value = R.string.load_screen_exception
                    else -> _exceptionId.value = R.string.unknown_exception
                }
            } else {
                _screensLoaded.setValue(true)
            }
            Timber.i("ViewModel 1 end ${Thread.currentThread().name}")
        }
    }
}