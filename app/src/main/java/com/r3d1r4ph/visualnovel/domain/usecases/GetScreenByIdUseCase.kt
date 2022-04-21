package com.r3d1r4ph.visualnovel.domain.usecases

import com.r3d1r4ph.visualnovel.domain.interfaces.ScreenRepository
import com.r3d1r4ph.visualnovel.domain.models.Screen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface GetScreenByIdUseCase : UseCase<Int, Result<Screen>>

class GetScreenByIdUseCaseImpl @Inject constructor(
    private val screenRepository: ScreenRepository
) : GetScreenByIdUseCase {
    override suspend operator fun invoke(input: Int): Result<Screen> = withContext(Dispatchers.IO) {
        try {
            Result.success(screenRepository.getScreenById(input))
        } catch (t: Throwable) {
            Result.failure(t)
        }
    }
}