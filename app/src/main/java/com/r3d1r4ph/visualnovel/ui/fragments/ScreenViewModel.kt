package com.r3d1r4ph.visualnovel.ui.fragments

import androidx.lifecycle.*
import androidx.navigation.NavDirections
import com.r3d1r4ph.visualnovel.common.exceptions.UnknownException
import com.r3d1r4ph.visualnovel.data.ScreenDataSource
import com.r3d1r4ph.visualnovel.domain.Screen
import com.r3d1r4ph.visualnovel.domain.ScreenTypeEnum
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class ScreenViewModel @Inject constructor(
    private val screenDataSource: ScreenDataSource
) : ViewModel() {

    private val _screen = MutableLiveData<Screen>()
    val screen: LiveData<Screen>
        get() = _screen.map { it }

    private val _screenType = MutableLiveData<ScreenTypeEnum>()
    val screenType: LiveData<ScreenTypeEnum>
        get() = _screenType.map { it }

    private val _exception = MutableLiveData<Exception>()
    val exception: LiveData<Exception>
        get() = _exception.map { it }

    fun determineScreenType(screenId: Int) {
        viewModelScope.launch {
            val result = screenDataSource.getScreenById(screenId)
            if (result.isSuccess) {
                _screenType.postValue(
                    result.getOrNull()?.screenType
                )
            } else {
                _exception.postValue(
                    (result.exceptionOrNull() ?: UnknownException()) as Exception
                )
            }
        }
    }

    fun getScreenById(screenId: Int) {
        viewModelScope.launch {
            val result = screenDataSource.getScreenById(screenId)
            if (result.isSuccess) {
                result.getOrNull()?.let {
                    _screen.postValue(it)
                }
            } else {
                _exception.postValue(
                    (result.exceptionOrNull() ?: UnknownException()) as Exception
                )
            }
        }
    }

    fun getScreenType(screenId: Int): ScreenTypeEnum? {
        val result = screenDataSource.getScreenById(screenId)
        return if (result.isSuccess) {
            result.getOrNull()?.screenType
        } else {
            _exception.value = (result.exceptionOrNull() ?: UnknownException()) as Exception
            null
        }
    }
}