package com.r3d1r4ph.visualnovel.domain.interfaces

import com.r3d1r4ph.visualnovel.domain.models.Screen
import com.r3d1r4ph.visualnovel.domain.models.ScreenTypeEnum

interface ScreenRepository {
    suspend fun getScreenById(id: Int): Screen
    suspend fun getScreenTypeById(id: Int): ScreenTypeEnum
    fun isScreensLoaded(): Boolean
    suspend fun loadScreens(jsonString: String): Boolean
}