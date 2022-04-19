package com.r3d1r4ph.visualnovel.domain.usecases

import com.r3d1r4ph.visualnovel.domain.ScreenRepository
import com.r3d1r4ph.visualnovel.domain.models.Screen
import com.r3d1r4ph.visualnovel.domain.models.ScreenTypeEnum
import javax.inject.Inject

class GetScreenTypeByIdUseCase @Inject constructor(
    private val screenRepository: ScreenRepository
) : UseCase<Int, Result<ScreenTypeEnum>> {
    override operator fun invoke(input: Int): Result<ScreenTypeEnum> =
        try {
            Result.success(screenRepository.getScreenTypeById(input))
        } catch (t: Throwable) {
            Result.failure(t)
        }
}