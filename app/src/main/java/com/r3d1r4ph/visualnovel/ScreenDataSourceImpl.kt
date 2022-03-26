package com.r3d1r4ph.visualnovel

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ScreenDataSourceImpl @Inject constructor(
    @ApplicationContext private val applicationContext: Context
) : ScreenDataSource {

    private val screenList = listOf<Screen>()

    override fun getScreenById(id: Int): ResultWrapper<Screen> {
        if (screenList.isEmpty()) {
            loadScreenList()
        }

        return if (screenList.isEmpty()) {
            ResultWrapper.Failure(LoadScreensException())
        } else {
            ResultWrapper.Success(screenList[id - 1])
        }
    }

    private fun loadScreenList() {
        val jsonString =
            JsonController.getJsonDataFromAsset(applicationContext, "visual_novel.jsonc")
        screenList.plus(jsonString?.let { JsonController.parseJsonToObjectByType<List<Screen>>(it) })
    }
}