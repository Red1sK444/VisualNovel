package com.r3d1r4ph.visualnovel.data

import com.r3d1r4ph.visualnovel.common.exceptions.LoadScreensException
import com.r3d1r4ph.visualnovel.data.dto.ScreenDto
import com.r3d1r4ph.visualnovel.data.utils.JsonParser
import javax.inject.Inject

class ScreenDataSourceImpl @Inject constructor() : ScreenDataSource {

    private val screenList = mutableListOf<ScreenDto>()

    override fun getScreenById(id: Int): ScreenDto {
        return if (screenList.isEmpty()) {
            throw LoadScreensException()
        } else {
            screenList[id - 1]
        }
    }

    override fun getScreenCount() = screenList.size

    override fun loadScreens(jsonString: String): Boolean =
        screenList.addAll(JsonParser.parseJsonToClassByType<List<ScreenDto>>(jsonString))
}