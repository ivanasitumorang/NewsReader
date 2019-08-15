package com.azuka.newsreader.newsCategories.trending


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.azuka.newsreader.R

import com.azuka.newsreader.databinding.FragmentTrendingBinding
import com.azuka.newsreader.homeScreens.HomeFragmentDirections
import com.azuka.newsreader.newsCategories.ArticleClickListener
import com.azuka.newsreader.newsCategories.NewsArticlesAdapter

class TrendingFragment : Fragment() {

    private val viewModel: TrendingViewModel by lazy {
        ViewModelProviders.of(this).get(TrendingViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentTrendingBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        setHasOptionsMenu(true)
        Log.i("CategoriesArticles", "Trending")
        val adapter = NewsArticlesAdapter(ArticleClickListener { article ->
            viewModel.displayArticleDetail(article)
        })

        binding.articleList.adapter = adapter

        viewModel.articleList.observe(this, Observer { articles ->
            articles?.let {
                adapter.submitList(articles)
            }
        })

        viewModel.navigateToArticleDetail.observe(this, Observer { article ->
            if (article != null){
                this.parentFragment!!.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(article))
                viewModel.displayArticleDetailComplete()
            }
        })

        return binding.root
    }


}
