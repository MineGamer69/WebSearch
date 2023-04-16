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

    @GET("/api/Search/WebSearchAPI")
    @Headers(
        "X-RapidAPI-Key:4ca157c19fmsh9cb5e3b4c9fc3fcp14b886jsn8b1bd93436f9",
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
        "X-RapidAPI-Key:PUT_KEY_HERE",
        "X-RapidAPI-Host:contextualwebsearch-websearch-v1.p.rapidapi.com"
    )
    fun imageSearchResponse(
        @Query("q") query: String,
        @Query("pageNumber") pageNumber: Int = 1,
        @Query("pageSize") pageSize: Int = 10,
        @Query("autoCorrect") autoCorrect: Boolean = true,
        @Query("safeSearch") safeSearch: Boolean = true
    ): Call<imageDataX>

    @GET("/api/Search/NewsSearchAPI")
    @Headers(
        "X-RapidAPI-Key:PUT_KEY_HERE",
        "X-RapidAPI-Host:contextualwebsearch-websearch-v1.p.rapidapi.com"
    )
    fun newsSearchResponse(
        @Query("q") query: String,
        @Query("pageNumber") pageNumber: Int = 1,
        @Query("pageSize") pageSize: Int = 10,
        @Query("autoCorrect") autoCorrect: Boolean = true,
        @Query("safeSearch") safeSearch: Boolean = true
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
