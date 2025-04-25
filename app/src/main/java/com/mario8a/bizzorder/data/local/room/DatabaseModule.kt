package com.mario8a.bizzorder.data.local.room

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    fun provideDatabase(
        application: Application
    ): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "room_database.db")
            .build()
    }

    fun provideOrderDao(
        appDatabase: AppDatabase
    ): OrderDao {
        return appDatabase.orderDao()
    }

    suspend fun providePreOrderDao(
        appDatabase: AppDatabase
    ): PreOrderDao {
        return appDatabase.preOrderDao()
    }

}