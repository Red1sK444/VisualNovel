package com.r3d1r4ph.visualnovel.di

import com.r3d1r4ph.visualnovel.data.ScreenRepository
import com.r3d1r4ph.visualnovel.data.ScreenRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindScreenRepository(
        screenRepositoryImpl: ScreenRepositoryImpl
    ): ScreenRepository
}