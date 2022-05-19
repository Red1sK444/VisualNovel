package com.r3d1r4ph.visualnovel.domain.usecases

import com.r3d1r4ph.visualnovel.common.exceptions.LoadScreensException
import com.r3d1r4ph.visualnovel.domain.interfaces.ScreenRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface LoadScreensUseCase : UseCase<String, Result<Boolean>>

class LoadScreensUseCaseImpl @Inject constructor(
    private val screenRepository: ScreenRepository
) : LoadScreensUseCase {
    override suspend operator fun invoke(input: String): Result<Boolean> =
        withContext(Dispatchers.IO) {
            if (screenRepository.loadScreens(input)) {
                Result.success(true)
            } else {
                Result.failure(LoadScreensException())
            }
        }
}