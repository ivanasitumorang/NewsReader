package com.azuka.newsreader.newsCategories.trending

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.azuka.newsreader.network.Article
import com.azuka.newsreader.network.NewsApi
import com.azuka.newsreader.network.NewsApiStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class TrendingViewModel : ViewModel() {

    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _articleList = MutableLiveData<List<Article>>()
    val articleList: LiveData<List<Article>>
        get() = _articleList

    private val _status = MutableLiveData<NewsApiStatus>()
    val status: LiveData<NewsApiStatus>
        get() = _status

    init {
        getArticles()
    }

    fun getArticles() {
        coroutineScope.launch {
            val getArticlesDeferred = NewsApi.
                retrofitService.getArticlesTopHeadlines(NewsApi.API_KEY, "id")
            try {

                _status.value = NewsApiStatus.LOADING

                val results = getArticlesDeferred.await()
                _status.value = NewsApiStatus.DONE
                if (results.totalResults > 0){
                    Log.i("NewsArticles", "Total result ${results.totalResults}")
                    _articleList.value = results.articles
                }

            } catch (e: Exception){
                _status.value = NewsApiStatus.ERROR
                _articleList.value = ArrayList()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}