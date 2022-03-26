package com.r3d1r4ph.visualnovel.data

import com.r3d1r4ph.visualnovel.domain.Screen
import com.r3d1r4ph.visualnovel.utils.ResultWrapper

interface ScreenDataSource {
    fun getScreenById(id: Int): ResultWrapper<Screen>
}