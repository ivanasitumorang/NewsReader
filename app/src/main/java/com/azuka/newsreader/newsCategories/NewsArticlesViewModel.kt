package com.azuka.newsreader.newsCategories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.azuka.newsreader.network.Article
import com.azuka.newsreader.network.NewsApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class NewsArticlesViewModel : ViewModel() {

    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _articleList = MutableLiveData<List<Article>>()
    val articleList: LiveData<List<Article>>
        get() = _articleList

    init {
        getArticles()
    }

    private fun getArticles() {
        coroutineScope.launch {
            val getArticlesDeferred = NewsApi.
                retrofitService.getArticlesTopHeadlines(NewsApi.API_KEY, "id")
            try {
                val results = getArticlesDeferred.await()
                if (results.totalResults > 0){
                    Log.i("NewsArticles", "Total result ${results.totalResults}")
                    _articleList.value = results.articles
                }

            } catch (e: Exception){
                _articleList.value = ArrayList()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}