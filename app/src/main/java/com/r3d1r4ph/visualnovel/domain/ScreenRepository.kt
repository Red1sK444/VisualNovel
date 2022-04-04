package com.r3d1r4ph.visualnovel.domain

interface ScreenRepository {
    fun getScreenById(id: Int): Result<Screen>
    fun getScreenTypeById(id: Int): Result<ScreenTypeEnum>
    fun isScreensLoaded(): Boolean
    fun loadScreens(jsonString: String): Boolean
}