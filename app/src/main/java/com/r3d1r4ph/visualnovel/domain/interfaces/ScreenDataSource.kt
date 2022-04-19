package com.r3d1r4ph.visualnovel.domain.interfaces

import com.r3d1r4ph.visualnovel.data.dto.ScreenDto

interface ScreenDataSource {
    suspend fun getScreenById(id: Int): ScreenDto
    suspend fun loadScreens(jsonString: String): Boolean
    fun getScreenCount(): Int
}