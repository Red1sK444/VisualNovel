package com.r3d1r4ph.visualnovel.domain.usecases

import com.r3d1r4ph.visualnovel.common.exceptions.LoadScreensException
import com.r3d1r4ph.visualnovel.domain.ScreenRepository
import com.r3d1r4ph.visualnovel.domain.models.Screen
import javax.inject.Inject

class LoadScreensUseCase @Inject constructor(
    private val screenRepository: ScreenRepository
) : UseCase<String, Result<Boolean>> {
    override operator fun invoke(input: String): Result<Boolean> =
        if (screenRepository.loadScreens(input)) {
            Result.success(true)
        } else {
            Result.failure(LoadScreensException())
        }
}