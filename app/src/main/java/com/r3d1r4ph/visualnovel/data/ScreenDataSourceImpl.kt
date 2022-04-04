package com.r3d1r4ph.visualnovel.data

import com.r3d1r4ph.visualnovel.common.exceptions.LoadScreensException
import com.r3d1r4ph.visualnovel.data.dto.ScreenDto
import com.r3d1r4ph.visualnovel.data.utils.Utils
import com.r3d1r4ph.visualnovel.domain.Screen
import javax.inject.Inject

class ScreenDataSourceImpl @Inject constructor() : ScreenDataSource {

    private val screenList = mutableListOf<ScreenDto>()

    override fun getScreenById(id: Int): Result<Screen> {
        return if (screenList.isEmpty()) {
            Result.failure(LoadScreensException())
        } else {
            Result.success(screenList[id - 1].toDomain())
        }
    }

    override fun isScreensLoaded() = screenList.isNotEmpty()

    override fun loadScreens(jsonString: String): Boolean {
        screenList.addAll(Utils.parseJsonToClassByType<List<ScreenDto>>(jsonString))
        return isScreensLoaded()
    }
}