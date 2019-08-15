package com.azuka.newsreader.homeScreens

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import com.azuka.newsreader.newsCategories.categories.CategoriesFragment
import com.azuka.newsreader.newsCategories.trending.TrendingFragment

// Since this is an object collection, use a FragmentStatePagerAdapter,
// and NOT a FragmentPagerAdapter.

class HomePagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    private val TITLES = arrayOf(
        "Trending",
        FragmentTitle.BUSINESS,
        FragmentTitle.TECHNOLOGY,
        FragmentTitle.SPORTS
    )

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                Log.i("PagerAdapter", "Position 0")
                return TrendingFragment()
            }

            1 -> {
                Log.i("PagerAdapter", "Position 1")
                return CategoriesFragment(FragmentTitle.BUSINESS)
            }

            2 -> {
                Log.i("PagerAdapter", "Position 2")
                return CategoriesFragment(FragmentTitle.TECHNOLOGY)
            }

            3 -> {
                Log.i("PagerAdapter", "Position 3")
                return CategoriesFragment(FragmentTitle.SPORTS)
            }

            else -> {
                Log.i("PagerAdapter", "Position default")
                return TrendingFragment()
            }
        }
    }

    override fun getCount(): Int = 4

    override fun getPageTitle(position: Int): CharSequence {
        return TITLES[position]
    }

    object FragmentTitle {
        const val BUSINESS = "Business"
        const val TECHNOLOGY = "Technology"
        const val SPORTS = "Sports"
    }

}