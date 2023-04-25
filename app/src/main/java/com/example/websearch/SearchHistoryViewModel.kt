package com.example.websearch

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchHistoryViewModel(application: Application) : AndroidViewModel(application) {

    private val searchHistoryDao: SearchHistoryDAO =
        SearchHistoryDatabase.getDatabase(application).searchHistoryDao()

    private val _searchHistory: MutableLiveData<List<SearchHistoryEntity>> =
        MutableLiveData()
    val searchHistory: LiveData<List<SearchHistoryEntity>>
        get() = _searchHistory

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _searchHistory.postValue(searchHistoryDao.getAll())
        }
    }
    fun insertSearchHistory(searchHistoryEntity: SearchHistoryEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            searchHistoryDao.insert(searchHistoryEntity)
        }
    }

    fun clearSearchHistory() {
        viewModelScope.launch(Dispatchers.IO) {
            searchHistoryDao.deleteAll()
        }
    }
}