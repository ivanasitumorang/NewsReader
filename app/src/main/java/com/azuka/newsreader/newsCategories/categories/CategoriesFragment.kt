package com.azuka.newsreader.newsCategories.categories


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController

import com.azuka.newsreader.databinding.FragmentCategoriesBinding
import com.azuka.newsreader.homeScreens.HomeFragmentDirections
import com.azuka.newsreader.newsCategories.ArticleClickListener
import com.azuka.newsreader.newsCategories.NewsArticlesAdapter

class CategoriesFragment(val category: String) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentCategoriesBinding.inflate(inflater)
        binding.lifecycleOwner = this
        val viewModelFactory = CategoriesViewModelFactory(category)
        val categoriesViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(CategoriesViewModel::class.java)

        binding.viewModel = categoriesViewModel
        setHasOptionsMenu(true)

        val adapter = NewsArticlesAdapter(ArticleClickListener { article ->
            categoriesViewModel.displayArticleDetail(article)
        })

        binding.articleList.adapter = adapter

        categoriesViewModel.articleList.observe(this, Observer { articles ->
            articles?.let {
                adapter.submitList(articles)
            }
        })

        categoriesViewModel.navigateToArticleDetail.observe(this, Observer { article ->
            if (article != null){
                this.parentFragment!!.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(article))
                categoriesViewModel.displayArticleDetailComplete()
            }
        })

        return binding.root
    }


}
