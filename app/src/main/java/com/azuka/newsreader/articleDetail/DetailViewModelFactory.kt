package com.azuka.newsreader.articleDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.azuka.newsreader.network.Article
import java.lang.IllegalArgumentException

class DetailViewModelFactory(private val article: Article) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)){
            return DetailViewModel(article) as T
        }

        throw IllegalArgumentException("Unknown View Model class")
    }

}