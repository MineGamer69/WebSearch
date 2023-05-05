//Made by Aaryan Kapoor and Matt Nova

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
//Implemented MutableLiveData instead of LiveData due to editing
    private val _searchHistory: MutableLiveData<List<SearchHistoryEntity>> =
        MutableLiveData()
    val searchHistory: LiveData<List<SearchHistoryEntity>>
        get() = _searchHistory

// this displays all the search history data
    init {
        viewModelScope.launch(Dispatchers.IO) {
            _searchHistory.postValue(searchHistoryDao.getAllSearchHistory())
        }
    }
    //inserts the search history
    fun insertSearchHistory(searchHistoryEntity: SearchHistoryEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            searchHistoryDao.insert(searchHistoryEntity)
        }
    }
//clearing search history
    fun clearSearchHistory() {
        viewModelScope.launch(Dispatchers.IO) {
            searchHistoryDao.deleteAll()
            _searchHistory.postValue(searchHistoryDao.getAllSearchHistory())
        }
    }

}