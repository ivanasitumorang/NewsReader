package com.azuka.newsreader.homeScreens

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.azuka.newsreader.newsCategories.lifestyle.CategoriesFragment
import com.azuka.newsreader.newsCategories.politics.PoliticsFragment
import com.azuka.newsreader.newsCategories.sport.SportsFragment
import com.azuka.newsreader.newsCategories.trending.TrendingFragment

// Since this is an object collection, use a FragmentStatePagerAdapter,
// and NOT a FragmentPagerAdapter.

class HomePagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val TITLES = arrayOf(
        "Trending",
        FragmentTitle.LIFESTYLES,
        FragmentTitle.POLITICS,
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
                return CategoriesFragment(FragmentTitle.LIFESTYLES)
            }

            2 -> {
                Log.i("PagerAdapter", "Position 2")
                return CategoriesFragment(FragmentTitle.POLITICS)
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

    override fun getCount(): Int = TITLES.size

    override fun getPageTitle(position: Int): CharSequence {
        return TITLES[position]
    }

    object FragmentTitle {
        const val LIFESTYLES = "Lifestyles"
        const val POLITICS = "Politics"
        const val SPORTS = "Sports"
    }

}