package com.azuka.newsreader.newsCategories.trending


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.azuka.newsreader.R
import com.azuka.newsreader.databinding.FragmentTrendingBinding
import com.azuka.newsreader.newsCategories.NewsArticlesAdapter
import com.azuka.newsreader.newsCategories.NewsArticlesViewModel

class TrendingFragment : Fragment() {

    private val viewModel: NewsArticlesViewModel by lazy {
        ViewModelProviders.of(this).get(NewsArticlesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentTrendingBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        setHasOptionsMenu(true)

        val adapter = NewsArticlesAdapter()

        binding.articleList.adapter = adapter

        viewModel.articleList.observe(this, Observer { articles ->
            articles?.let {
                adapter.submitList(articles)
            }
        })

        return binding.root
    }


}
