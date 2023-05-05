//Made by Aaryan Kapoor and Matt Nova

package com.example.websearch

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
//DAO file for my inputs and deletes and displaying the search history
@Dao
interface SearchHistoryDAO {
    @Query("SELECT * FROM search_history")
    suspend fun getAll(): List<SearchHistoryEntity>

    @Insert
    suspend fun insert(searchHistoryEntity: SearchHistoryEntity)

    @Query("DELETE FROM search_history")
    suspend fun deleteAll()

    @Query("SELECT * FROM search_history ORDER BY timeStamp DESC")
    suspend fun getAllSearchHistory(): List<SearchHistoryEntity>

    @Query("SELECT * FROM search_history ORDER BY timeStamp DESC LIMIT 1")
    suspend fun getLatestSearch(): SearchHistoryEntity?
}