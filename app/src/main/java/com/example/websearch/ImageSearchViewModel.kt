package com.example.websearch

//Made By Aaryan Kapoor & Matt Nova
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ImageSearchViewModel : ViewModel() {

    val searchResults = MutableLiveData<List<ImageValue>>()
//API setup
    fun performSearch(query: String, safeSearchEnabled: Boolean) {
        APIinterface.create(query).imageSearchResponse(query,1,10,true, safeSearchEnabled).enqueue(object : Callback<imageDataX> {
            override fun onResponse(call: Call<imageDataX>, response: Response<imageDataX>) {
                val results = response.body()?.value ?: emptyList()
                searchResults.postValue(results)
            }

            override fun onFailure(call: Call<imageDataX>, t: Throwable) {
                Log.d("crash", "API CALL FAILED!")
            }
        })
    }

}
