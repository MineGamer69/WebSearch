package com.example.websearch

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SearchHistoryDAO {
    @Query("SELECT * FROM search_history")
    suspend fun getAll(): List<SearchHistoryEntity>

    @Insert
    suspend fun insert(searchHistoryEntity: SearchHistoryEntity)

    @Query("DELETE FROM search_history")
    suspend fun deleteAll()
}