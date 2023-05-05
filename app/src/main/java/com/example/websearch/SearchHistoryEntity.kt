//Made by Aaryan Kapoor and Matt Nova

package com.example.websearch

import androidx.room.Entity
import androidx.room.PrimaryKey
//Our database entity class with the values in it.
@Entity(tableName = "search_history")
data class SearchHistoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val searchQuery: String,
    val safeSearch: Boolean,
    val timeStamp: Long,
    val searchType: String
)