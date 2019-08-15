package com.azuka.newsreader.newsCategories.categories

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

class CategoriesViewModel(category: String) : ViewModel(){

    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _articleList = MutableLiveData<List<Article>>()
    val articleList: LiveData<List<Article>>
        get() = _articleList

    private val _status = MutableLiveData<NewsApiStatus>()
    val status: LiveData<NewsApiStatus>
        get() = _status

    private val _category = MutableLiveData<String>()
    val category: LiveData<String>
        get() = _category

    private val _navigateToArticleDetail = MutableLiveData<Article>()
    val navigateToArticleDetail: LiveData<Article>
        get() = _navigateToArticleDetail

    init {
        _category.value = category
        getArticles()
    }

    fun getArticles() {
        coroutineScope.launch {
            val getArticlesDeferred = NewsApi.
                retrofitService.getArticlesByCategory(NewsApi.API_KEY, "id", category.value!!)
            try {

                _status.value = NewsApiStatus.LOADING

                val results = getArticlesDeferred.await()
                _status.value = NewsApiStatus.DONE
                if (results.totalResults > 0){
                    Log.i("CategoriesArticles", "Total result ${results.totalResults}")
                    _articleList.value = results.articles
                }

            } catch (e: Exception){
                _status.value = NewsApiStatus.ERROR
                _articleList.value = ArrayList()
            }
        }
    }

    fun displayArticleDetail(article: Article){
        _navigateToArticleDetail.value = article
    }

    fun displayArticleDetailComplete(){
        _navigateToArticleDetail.value = null
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}