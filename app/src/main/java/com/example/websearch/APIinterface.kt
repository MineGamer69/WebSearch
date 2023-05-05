//Made By Aaryan Kapoor & Matt Nova
package com.example.websearch

import android.os.Build
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import java.net.URLEncoder

// Backend for our api gets
interface APIinterface {
//Our backend class with api calls and queries
    @GET("/api/Search/WebSearchAPI")
    @Headers(
        BuildConfig.api_key,
        "X-RapidAPI-Host:contextualwebsearch-websearch-v1.p.rapidapi.com"
    )
    fun webSearchResponse(
        @Query("q") query: String,
        @Query("pageNumber") pageNumber: Int = 1,
        @Query("pageSize") pageSize: Int = 10,
        @Query("autoCorrect") autoCorrect: Boolean = true,
        @Query("safeSearch") safeSearchEnabled: Boolean
    ): Call<webDataX>

    @GET("/api/Search/ImageSearchAPI")
    @Headers(
        BuildConfig.api_key,
        "X-RapidAPI-Host:contextualwebsearch-websearch-v1.p.rapidapi.com"
    )
    fun imageSearchResponse(
        @Query("q") query: String,
        @Query("pageNumber") pageNumber: Int = 1,
        @Query("pageSize") pageSize: Int = 10,
        @Query("autoCorrect") autoCorrect: Boolean = true,
        @Query("safeSearch") safeSearchEnabled: Boolean
    ): Call<imageDataX>

    @GET("/api/search/NewsSearchAPI")
    @Headers(
        BuildConfig.api_key,
        "X-RapidAPI-Host:contextualwebsearch-websearch-v1.p.rapidapi.com"
    )
    fun newsSearchResponse(
        @Query("q") query: String,
        @Query("pageNumber") pageNumber: Int = 1,
        @Query("pageSize") pageSize: Int = 10,
        @Query("autoCorrect") autoCorrect: Boolean = true,
        @Query("safeSearch") safeSearchEnabled: Boolean
    ): Call<newsDataX>

    companion object {

        fun create(searchTerm: String): APIinterface {

            val encodedSearchTerm = URLEncoder.encode(searchTerm, "UTF-8")
            val baseUrl = "https://contextualwebsearch-websearch-v1.p.rapidapi.com"
            val BASE_URL =
                "https://contextualwebsearch-websearch-v1.p.rapidapi.com/"

            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(logging) // <-- this is the important line!
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .client(httpClient.build())
                .build()
            return retrofit.create(APIinterface::class.java)
        }
    }
}