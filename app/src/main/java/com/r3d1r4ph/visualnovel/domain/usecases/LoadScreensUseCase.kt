package com.r3d1r4ph.visualnovel.domain.usecases

import com.r3d1r4ph.visualnovel.common.exceptions.LoadScreensException
import com.r3d1r4ph.visualnovel.domain.interfaces.ScreenRepository
import javax.inject.Inject

interface LoadScreensUseCase : UseCase<String, Result<Boolean>>

class LoadScreensUseCaseImpl @Inject constructor(
    private val screenRepository: ScreenRepository
) : LoadScreensUseCase {
    override suspend operator fun invoke(input: String): Result<Boolean> =
        if (screenRepository.loadScreens(input)) {
            Result.success(true)
        } else {
            Result.failure(LoadScreensException())
        }
}