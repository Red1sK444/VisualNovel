package com.r3d1r4ph.visualnovel.domain.usecases

import com.r3d1r4ph.visualnovel.common.exceptions.LoadScreensException
import com.r3d1r4ph.visualnovel.domain.models.ScreenRepository
import javax.inject.Inject

class LoadScreensUseCase @Inject constructor(
    private val screenRepository: ScreenRepository
) {
    operator fun invoke(jsonString: String): Result<Boolean> =
        if (screenRepository.loadScreens(jsonString)) {
            Result.success(true)
        } else {
            Result.failure(LoadScreensException())
        }
}