//Made By Aaryan Kapoor & Matt Nova
package com.example.websearch

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsSearchViewModel : ViewModel() {

    val searchResults = MutableLiveData<List<NewsValue>>()

    fun performSearch(query: String, safeSearchEnabled: Boolean) {
        APIinterface.create(query).newsSearchResponse(query,1,10,true, safeSearchEnabled).enqueue(object : Callback<newsDataX> {
            override fun onResponse(call: Call<newsDataX>, response: Response<newsDataX>) {
                val results = response.body()?.value ?: emptyList()
                searchResults.postValue(results)
            }

            override fun onFailure(call: Call<newsDataX>, t: Throwable) {
                Log.d("crash", "API CALL FAILED!")
            }
        })
    }

}

