package com.r3d1r4ph.visualnovel.ui.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.r3d1r4ph.visualnovel.data.ScreenDataSource
import com.r3d1r4ph.visualnovel.domain.Screen
import com.r3d1r4ph.visualnovel.domain.ScreenTypes
import com.r3d1r4ph.visualnovel.utils.ResultWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
open class ScreenViewModel @Inject constructor(
    private val screenDataSource: ScreenDataSource
) : ViewModel() {

    private val _screen = MutableLiveData<Screen>()
    val screen: LiveData<Screen>
        get() = _screen

    private val _exception = MutableLiveData<Exception>()
    val exception: LiveData<Exception>
        get() = _exception

    fun getScreenType(screenId: Int): ScreenTypes? {
        return when (val result = screenDataSource.getScreenById(screenId)) {
            is ResultWrapper.Success -> result.value.screenType
            is ResultWrapper.Failure -> {
                _exception.value = result.exception
                null
            }
        }
    }

    fun getScreenById(screenId: Int) {
        when (val result = screenDataSource.getScreenById(screenId)) {
            is ResultWrapper.Success -> {
                val wtf: Screen = result.value
                _screen.value = wtf
            }
            is ResultWrapper.Failure -> _exception.value = result.exception
        }
    }
}