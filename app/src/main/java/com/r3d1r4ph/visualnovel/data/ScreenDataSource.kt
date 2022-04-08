package com.r3d1r4ph.visualnovel.data

import com.r3d1r4ph.visualnovel.data.dto.ScreenDto

interface ScreenDataSource {
    fun getScreenById(id: Int): ScreenDto
    fun loadScreens(jsonString: String): Boolean
    fun getScreenCount(): Int
}