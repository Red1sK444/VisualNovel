package com.r3d1r4ph.visualnovel.di

import com.r3d1r4ph.visualnovel.domain.usecases.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun bindsGetScreenByIdUseCase(
        getScreenByIdUseCaseImpl: GetScreenByIdUseCaseImpl
    ): GetScreenByIdUseCase

    @Binds
    abstract fun bindsGetScreenTypeByIdUseCase(
        getScreenTypeByIdUseCaseImpl: GetScreenTypeByIdUseCaseImpl
    ): GetScreenTypeByIdUseCase

    @Binds
    abstract fun bindsLoadScreensUseCase(
        loadScreensUseCaseImpl: LoadScreensUseCaseImpl
    ): LoadScreensUseCase
}