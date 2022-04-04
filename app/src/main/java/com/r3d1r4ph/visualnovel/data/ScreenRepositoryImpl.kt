package com.r3d1r4ph.visualnovel.data

import com.r3d1r4ph.visualnovel.common.exceptions.UnknownException
import com.r3d1r4ph.visualnovel.domain.Screen
import com.r3d1r4ph.visualnovel.domain.ScreenRepository
import com.r3d1r4ph.visualnovel.domain.ScreenTypeEnum
import javax.inject.Inject

class ScreenRepositoryImpl @Inject constructor(
    private val screenDataSource: ScreenDataSource
) : ScreenRepository {

    override fun getScreenById(id: Int): Result<Screen> =
        screenDataSource.getScreenById(id)

    override fun getScreenTypeById(id: Int): Result<ScreenTypeEnum> {
        val result = screenDataSource.getScreenById(id)
        return if (result.isSuccess) {
            try {
                val screen = result.getOrThrow()
                Result.success(screen.screenType)
            } catch (t: Throwable) {
                Result.failure(UnknownException())
            }
        } else {
            Result.failure(result.exceptionOrNull() ?: UnknownException())
        }
    }

    override fun isScreensLoaded() =
        screenDataSource.isScreensLoaded()

    override fun loadScreens(jsonString: String) =
        screenDataSource.loadScreens(jsonString)
}