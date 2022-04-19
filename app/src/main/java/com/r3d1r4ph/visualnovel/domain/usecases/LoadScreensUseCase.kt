package com.r3d1r4ph.visualnovel.domain.usecases

import com.r3d1r4ph.visualnovel.common.exceptions.LoadScreensException
import com.r3d1r4ph.visualnovel.domain.interfaces.ScreenRepository
import javax.inject.Inject

class LoadScreensUseCase @Inject constructor(
    private val screenRepository: ScreenRepository
) : UseCase<String, Result<Boolean>> {
    override suspend operator fun invoke(input: String): Result<Boolean> =
        if (screenRepository.loadScreens(input)) {
            Result.success(true)
        } else {
            Result.failure(LoadScreensException())
        }
}