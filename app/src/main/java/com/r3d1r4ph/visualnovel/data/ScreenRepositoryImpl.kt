package com.r3d1r4ph.visualnovel.data

import com.r3d1r4ph.visualnovel.domain.models.Screen
import com.r3d1r4ph.visualnovel.domain.ScreenRepository
import com.r3d1r4ph.visualnovel.domain.models.ScreenTypeEnum
import javax.inject.Inject

class ScreenRepositoryImpl @Inject constructor(
    private val screenDataSource: ScreenDataSource
) : ScreenRepository {

    override fun getScreenById(id: Int): Screen =
        screenDataSource.getScreenById(id).toDomain()

    override fun getScreenTypeById(id: Int): ScreenTypeEnum =
        screenDataSource.getScreenById(id).screenType

    override fun isScreensLoaded() =
        screenDataSource.getScreenCount() > 0

    override fun loadScreens(jsonString: String) =
        screenDataSource.loadScreens(jsonString)
}