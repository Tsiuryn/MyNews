package com.ts.alex.mynews.data.network

import com.ts.alex.mynews.BuildConfig.API_KEY
import com.ts.alex.mynews.data.network.entity.NewsEntity
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface RestApi {

    @GET("v2/top-headlines")
    fun getNewsByCountry (
        @Query("country") domainCountry: String,
        @Query("category") category: String = "business",
        @Query("apiKey") key: String? = API_KEY,
    ): Deferred<NewsEntity>

}