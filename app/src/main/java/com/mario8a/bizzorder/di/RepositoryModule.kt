package com.mario8a.bizzorder.di

import com.mario8a.bizzorder.data.LocalDataStorage
import com.mario8a.bizzorder.data.OrderRepositoryImpl
import com.mario8a.bizzorder.data.PreOrderRepositoryImpl
import com.mario8a.bizzorder.data.remote.RemoteDataStorage
import com.mario8a.bizzorder.domain.OrderRepository
import com.mario8a.bizzorder.domain.PreOrderRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideOrderRepository(
        remoteDataStorage: RemoteDataStorage,
        localDataStorage: LocalDataStorage
    ): OrderRepository {
        return OrderRepositoryImpl(
            remoteDataStorage = remoteDataStorage,
            localDataStorage = localDataStorage
        )
    }

    @Singleton
    @Provides
    fun providePreOrderRepository(
        remoteDataStorage: RemoteDataStorage,
        localDataStorage: LocalDataStorage
    ): PreOrderRepository {
        return PreOrderRepositoryImpl(
            remoteDataStorage = remoteDataStorage,
            localDataStorage = localDataStorage
        )
    }
}