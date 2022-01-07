package com.latihan.rmovies.ui.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.latihan.rmovies.ui.main.MainActivity
import com.latihan.rmovies.ui.movies.MoviesFragment
import com.latihan.rmovies.ui.tvshows.TvShowsFragment

class SectionPagerAdapter(activity: MainActivity) :
    FragmentStateAdapter(activity) {

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