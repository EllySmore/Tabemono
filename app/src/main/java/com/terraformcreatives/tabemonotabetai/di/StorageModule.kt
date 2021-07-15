package com.terraformcreatives.tabemonotabetai.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.terraformcreatives.tabemonotabetai.persist.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ApplicationComponent::class)
class StorageModule {

    @Provides
    fun getAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java, "tabemono-database"
        ).build()
    }
}