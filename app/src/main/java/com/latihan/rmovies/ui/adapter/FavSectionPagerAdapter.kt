package com.latihan.rmovies.ui.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.latihan.rmovies.ui.favorite.FavoriteFragment
import com.latihan.rmovies.ui.favorite.favmovies.FavMoviesFragment
import com.latihan.rmovies.ui.favorite.favshows.FavShowsFragment
import com.latihan.rmovies.ui.home.HomeFragment
import com.latihan.rmovies.ui.movies.MoviesFragment
import com.latihan.rmovies.ui.tvshows.TvShowsFragment

class FavSectionPagerAdapter(activity: FavoriteFragment) :
    FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = FavMoviesFragment()
            1 -> fragment = FavShowsFragment()
        }
        return fragment as Fragment
    }

}