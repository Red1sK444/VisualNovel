package com.r3d1r4ph.visualnovel.data

import com.r3d1r4ph.visualnovel.domain.Screen
import com.r3d1r4ph.visualnovel.domain.ScreenTypeEnum

interface ScreenRepository {
    fun getScreenById(id: Int): Result<Screen>
    fun getScreenTypeById(id: Int): Result<ScreenTypeEnum>
}