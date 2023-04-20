package com.example.websearch

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SearchHistoryEntityTest {
    private lateinit var database: SearchHistoryDatabase
    private lateinit var searchHistoryDao: SearchHistoryDAO

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            SearchHistoryDatabase::class.java
        ).build()

        searchHistoryDao = database.searchHistoryDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertAndGetSearchHistory() = runBlocking {
        // Create a new search history entity
        val searchHistory = SearchHistoryEntity(
            searchQuery = "test query",
            safeSearch = true,
            timeStamp = System.currentTimeMillis(),
            searchType = "web"
        )

        // Insert the search history into the database
        searchHistoryDao.insert(searchHistory)

        // Get the search history from the database
        val result = searchHistoryDao.getAll()

        // Check that the retrieved search history is the same as the original
        assertEquals(searchHistory, result)
    }

}
