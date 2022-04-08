package com.r3d1r4ph.visualnovel.di

import com.r3d1r4ph.visualnovel.data.ScreenDataSource
import com.r3d1r4ph.visualnovel.data.ScreenDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun bindsScreenDataSource(
        screenDataSourceImpl: ScreenDataSourceImpl
    ): ScreenDataSource
}