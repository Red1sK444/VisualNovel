package com.r3d1r4ph.visualnovel.domain

import com.r3d1r4ph.visualnovel.domain.models.Screen
import com.r3d1r4ph.visualnovel.domain.models.ScreenTypeEnum

interface ScreenRepository {
    fun getScreenById(id: Int): Screen
    fun getScreenTypeById(id: Int): ScreenTypeEnum
    fun isScreensLoaded(): Boolean
    fun loadScreens(jsonString: String): Boolean
}