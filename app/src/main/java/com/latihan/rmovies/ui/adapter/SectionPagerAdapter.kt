package com.latihan.rmovies.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.latihan.rmovies.ui.movies.MoviesFragment
import com.latihan.rmovies.ui.tvshows.TvShowsFragment

class SectionPagerAdapter(
    fm: FragmentManager,
    lifeCycle: Lifecycle
): FragmentStateAdapter(fm, lifeCycle) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = MoviesFragment()
            1 -> fragment = TvShowsFragment()
        }
        return fragment as Fragment
    }

}