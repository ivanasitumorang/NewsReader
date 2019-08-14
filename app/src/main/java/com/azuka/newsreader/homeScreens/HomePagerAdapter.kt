package com.azuka.newsreader.homeScreens

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.azuka.newsreader.newsCategories.lifestyle.LifestylesFragment
import com.azuka.newsreader.newsCategories.politics.PoliticsFragment
import com.azuka.newsreader.newsCategories.sport.SportsFragment
import com.azuka.newsreader.newsCategories.trending.TrendingFragment

// Since this is an object collection, use a FragmentStatePagerAdapter,
// and NOT a FragmentPagerAdapter.
class HomePagerAdapter (fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val TITLES = arrayOf("Trending", "Lifestyle", "Politics", "Sports")

    override fun getItem(position: Int): Fragment {
        when(position){
            0 -> {
                Log.i("PagerAdapter", "Position 0")
                return TrendingFragment()
            }

            1 -> {
                Log.i("PagerAdapter", "Position 1")
                return LifestylesFragment()
            }

            2 -> {
                Log.i("PagerAdapter", "Position 2")
                return PoliticsFragment()
            }

            3 -> {
                Log.i("PagerAdapter", "Position 3")
                return SportsFragment()
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

}