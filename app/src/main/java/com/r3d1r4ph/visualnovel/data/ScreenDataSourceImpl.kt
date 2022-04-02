package com.r3d1r4ph.visualnovel.data

import android.content.Context
import com.r3d1r4ph.visualnovel.BuildConfig
import com.r3d1r4ph.visualnovel.common.JsonController
import com.r3d1r4ph.visualnovel.common.exceptions.LoadScreensException
import com.r3d1r4ph.visualnovel.domain.Screen
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ScreenDataSourceImpl @Inject constructor(
    @ApplicationContext private val applicationContext: Context
) : ScreenDataSource {

    private val screenList = mutableListOf<Screen>()

    override fun getScreenById(id: Int): Result<Screen> {
        if (screenList.isEmpty()) {
            loadScreenList()
        }

        return if (screenList.isEmpty()) {
            Result.failure(LoadScreensException())
        } else {
            Result.success(screenList[id - 1])
        }
    }

    private fun loadScreenList() {
        val jsonString =
            JsonController.getJsonDataFromAsset(applicationContext, BuildConfig.SCRIPT_FILE_NAME)
        jsonString?.let {
            JsonController.parseJsonToObjectByType<List<Screen>>(it).toList()
        }?.let { screenList.addAll(it) }
    }
}