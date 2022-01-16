package com.latihan.rmovies.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.latihan.rmovies.R
import com.latihan.rmovies.databinding.FragmentHomeBinding
import com.latihan.rmovies.ui.adapter.SectionPagerAdapter


class HomeFragment : Fragment() {

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.movies, R.string.tv_show)
    }

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        initPagerAdapter()
        return binding.root
    }

    private fun initPagerAdapter() {
        val sectionPagerAdapter = SectionPagerAdapter(requireActivity().supportFragmentManager, lifecycle)
        val viewPager: ViewPager2 = binding.viewPager
        viewPager.adapter = sectionPagerAdapter
        val tabs: TabLayout = binding.tabLayout
        TabLayoutMediator(tabs, viewPager) { tabs, position ->
            tabs.text = resources.getString(
                TAB_TITLES[position]
            )
        }.attach()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}