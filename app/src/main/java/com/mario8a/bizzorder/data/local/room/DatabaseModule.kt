package com.mario8a.bizzorder.data.local.room

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        application: Application
    ): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "room_database.db")
            .build()
    }

    @Provides
    @Singleton
    fun provideOrderDao(
        appDatabase: AppDatabase
    ): OrderDao {
        return appDatabase.orderDao()
    }

    @Provides
    @Singleton
    fun providePreOrderDao(
        appDatabase: AppDatabase
    ): PreOrderDao {
        return appDatabase.preOrderDao()
    }

}