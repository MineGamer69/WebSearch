package com.example.websearch

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "search_history")
data class SearchHistoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val searchQuery: String,
    val safeSearch: Boolean,
    val timeStamp: Long,
    val searchType: String
)
