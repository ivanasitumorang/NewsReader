package com.azuka.newsreader.articleDetail


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders

import com.azuka.newsreader.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val article = DetailFragmentArgs.fromBundle(arguments!!).selectedArticle
        val viewModelFactory = DetailViewModelFactory(article)

        val viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(DetailViewModel::class.java)

        binding.viewModel = viewModel

        setHasOptionsMenu(true)

        (activity as AppCompatActivity).supportActionBar!!.title = "Detail"

        return binding.root
    }


}
