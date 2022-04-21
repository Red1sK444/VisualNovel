package com.r3d1r4ph.visualnovel.domain.usecases

import com.r3d1r4ph.visualnovel.domain.interfaces.ScreenRepository
import com.r3d1r4ph.visualnovel.domain.models.ScreenTypeEnum
import javax.inject.Inject

interface GetScreenTypeByIdUseCase : UseCase<Int, Result<ScreenTypeEnum>>

class GetScreenTypeByIdUseCaseImpl @Inject constructor(
    private val screenRepository: ScreenRepository
) : GetScreenTypeByIdUseCase {
    override suspend operator fun invoke(input: Int): Result<ScreenTypeEnum> =
        try {
            Result.success(screenRepository.getScreenTypeById(input))
        } catch (t: Throwable) {
            Result.failure(t)
        }
}