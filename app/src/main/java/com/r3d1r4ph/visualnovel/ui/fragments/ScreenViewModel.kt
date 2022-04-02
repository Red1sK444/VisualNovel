package com.r3d1r4ph.visualnovel.ui.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.r3d1r4ph.visualnovel.data.ScreenDataSource
import com.r3d1r4ph.visualnovel.domain.Screen
import com.r3d1r4ph.visualnovel.domain.ScreenTypes
import com.r3d1r4ph.visualnovel.utils.exceptions.UnknownException
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
        val result = screenDataSource.getScreenById(screenId)
        return if (result.isSuccess) {
            result.getOrNull()?.screenType
        } else {
            _exception.value = (result.exceptionOrNull() ?: UnknownException()) as Exception
            null
        }
    }

    fun getScreenById(screenId: Int) {
        val result = screenDataSource.getScreenById(screenId)
        if (result.isSuccess) {
            result.getOrNull()?.let {
                _screen.value = it
            }
        } else {
            _exception.value = (result.exceptionOrNull() ?: UnknownException()) as Exception
        }
    }
}