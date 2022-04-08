package com.r3d1r4ph.visualnovel.domain.usecases

import com.r3d1r4ph.visualnovel.domain.models.ScreenRepository
import com.r3d1r4ph.visualnovel.domain.models.ScreenTypeEnum
import javax.inject.Inject

class GetScreenTypeByIdUseCase @Inject constructor(
    private val screenRepository: ScreenRepository
) {
    operator fun invoke(screenId: Int): Result<ScreenTypeEnum> =
        try {
            Result.success(screenRepository.getScreenTypeById(screenId))
        } catch (t: Throwable) {
            Result.failure(t)
        }
}