package com.r3d1r4ph.visualnovel.di

import com.r3d1r4ph.visualnovel.domain.models.ScreenRepository
import com.r3d1r4ph.visualnovel.data.ScreenRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindScreenRepository(
        screenRepositoryImpl: ScreenRepositoryImpl
    ): ScreenRepository
}