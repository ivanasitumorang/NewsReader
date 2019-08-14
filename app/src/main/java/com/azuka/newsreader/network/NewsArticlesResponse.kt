package com.azuka.newsreader.network

data class NewsArticlesResponse<T> (
    val status: String,
    val totalResults: Int,
    val articles: List<T>
)