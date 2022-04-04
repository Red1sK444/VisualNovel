package com.r3d1r4ph.visualnovel.data

import com.r3d1r4ph.visualnovel.domain.Screen

interface ScreenDataSource {
    fun getScreenById(id: Int): Result<Screen>
    fun loadScreens(jsonString: String): Boolean
    fun isScreensLoaded(): Boolean
}