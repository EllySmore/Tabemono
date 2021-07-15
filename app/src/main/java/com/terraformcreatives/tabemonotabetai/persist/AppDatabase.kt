package com.terraformcreatives.tabemonotabetai.persist

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Recipe::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bookmarkedRecipeDao(): BookmarkedRecipeDao
}