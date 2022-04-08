package com.r3d1r4ph.visualnovel.domain.models

interface ScreenRepository {
    fun getScreenById(id: Int): Screen
    fun getScreenTypeById(id: Int): ScreenTypeEnum
    fun isScreensLoaded(): Boolean
    fun loadScreens(jsonString: String): Boolean
}