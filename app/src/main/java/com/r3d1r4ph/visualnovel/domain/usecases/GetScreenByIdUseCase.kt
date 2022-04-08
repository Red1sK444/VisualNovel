package com.r3d1r4ph.visualnovel.domain.usecases

import com.r3d1r4ph.visualnovel.domain.models.Screen
import com.r3d1r4ph.visualnovel.domain.models.ScreenRepository
import javax.inject.Inject

class GetScreenByIdUseCase @Inject constructor(
    private val screenRepository: ScreenRepository
) {
    operator fun invoke(screenId: Int): Result<Screen> =
        try {
            Result.success(screenRepository.getScreenById(screenId))
        } catch (t: Throwable) {
            Result.failure(t)
        }
}