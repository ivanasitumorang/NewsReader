package com.azuka.newsreader.homeScreens


import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.azuka.newsreader.R
import com.google.android.material.tabs.TabLayout


class HomeFragment : Fragment() {

    // When requested, this adapter returns a DemoObjectFragment,
    // representing an object in the collection.
    private lateinit var homePagerAdapter: HomePagerAdapter
    private lateinit var viewPager: ViewPager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i("Home", "onCreateView")
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.i("Home", "onViewCreated")
        homePagerAdapter = HomePagerAdapter(childFragmentManager)
        viewPager = view.findViewById(R.id.pager)
        viewPager.adapter = homePagerAdapter

        val tabLayout: TabLayout = view.findViewById(R.id.tab_layout)
        tabLayout.setupWithViewPager(viewPager)
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(p0: TabLayout.Tab?) {
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {
            }

            override fun onTabSelected(p0: TabLayout.Tab?) {
                viewPager.currentItem = p0!!.position
            }

        })
    }

    override fun onAttach(context: Context?) {
        Log.i("Home", "onAttach")
        super.onAttach(context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Log.i("Home", "onActivityCreated")
        super.onActivityCreated(savedInstanceState)
    }

    override fun onResume() {
        Log.i("Home", "onResume")
        super.onResume()
    }

    override fun onStart() {
        Log.i("Home", "onStart")
        super.onStart()
    }

    override fun onPause() {
        Log.i("Home", "onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.i("Home", "onStop")
        super.onStop()
    }

    override fun onDetach() {
        Log.i("Home", "onDetach")
        super.onDetach()
    }

    override fun onDestroyView() {
        Log.i("Home", "onDestroyView")
        super.onDestroyView()
    }

    override fun onDestroy() {
        Log.i("Home", "onDestroy")
        super.onDestroy()
    }

}
