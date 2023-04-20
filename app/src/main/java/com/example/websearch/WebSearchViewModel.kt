package com.example.websearch

//Made By Aaryan Kapoor & Matt Nova

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WebSearchViewModel : ViewModel() {

    val searchResults = MutableLiveData<List<Value>>()

    fun performSearch(query: String, safeSearchEnabled: Boolean) {
        APIinterface.create(query).webSearchResponse(query,1,10,true, safeSearchEnabled).enqueue(object : Callback<webDataX> {
            override fun onResponse(call: Call<webDataX>, response: Response<webDataX>) {
                val results = response.body()?.value ?: emptyList()
                searchResults.postValue(results)
            }

            override fun onFailure(call: Call<webDataX>, t: Throwable) {
                Log.d("crash", "API CALL FAILED!")
            }
        })
    }

}