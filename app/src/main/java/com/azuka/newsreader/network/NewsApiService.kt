package com.azuka.newsreader.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://newsapi.org/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val okHttpClient = OkHttpClient().newBuilder().build()

private val retrofit = Retrofit.Builder()
    .client(okHttpClient)
    .addConverterFactory(MoshiConverterFactory.create())
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

interface NewsApiService {
    @GET("v2/top-headlines")
    fun getArticlesTopHeadlines(
        @Query("apiKey") apiKey: String,
        @Query("country") country: String): Deferred<NewsArticlesResponse<Article>>

    fun getArticlesByCategory(
        @Query("apiKey") apiKey: String,
        @Query("country") country: String,
        @Query("category") category: String
    ): Deferred<NewsArticlesResponse<Article>>
}

object NewsApi {
    const val API_KEY = "12a73ae6bd25447e96fbe42b5a320ffc"
    val retrofitService: NewsApiService by lazy {
        retrofit.create(NewsApiService::class.java)
    }
}