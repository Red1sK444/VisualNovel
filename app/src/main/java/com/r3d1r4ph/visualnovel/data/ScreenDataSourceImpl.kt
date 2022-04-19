package com.r3d1r4ph.visualnovel.data

import com.r3d1r4ph.visualnovel.common.exceptions.LoadScreensException
import com.r3d1r4ph.visualnovel.data.dto.ScreenDto
import com.r3d1r4ph.visualnovel.data.utils.JsonParser
import com.r3d1r4ph.visualnovel.domain.interfaces.ScreenDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ScreenDataSourceImpl @Inject constructor() : ScreenDataSource {

    private val screenList = mutableListOf<ScreenDto>()

    override suspend fun getScreenById(id: Int): ScreenDto = withContext(Dispatchers.IO) {
        if (screenList.isEmpty()) {
            throw LoadScreensException()
        } else {
            screenList[id - 1]
        }
    }

    override fun getScreenCount() = screenList.size

    override suspend fun loadScreens(jsonString: String): Boolean = withContext(Dispatchers.IO) {
        screenList.addAll(JsonParser.parseJsonToClassByType<List<ScreenDto>>(jsonString))
    }
}