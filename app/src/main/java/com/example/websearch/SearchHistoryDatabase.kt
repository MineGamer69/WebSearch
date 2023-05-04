//Made By Aaryan Kapoor & Matt Nova
package com.example.websearch

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.room.migration.Migration

@Database(entities = [SearchHistoryEntity::class], version = 1)
abstract class SearchHistoryDatabase : RoomDatabase() {
    abstract fun searchHistoryDao(): SearchHistoryDAO

    companion object {
        @Volatile
        private var INSTANCE: SearchHistoryDatabase? = null

        fun getDatabase(context: Context): SearchHistoryDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SearchHistoryDatabase::class.java,
                    "search_history_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
