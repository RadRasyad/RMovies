package com.latihan.rmovies.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.latihan.rmovies.R
import com.latihan.rmovies.databinding.ActivityMainBinding
import com.latihan.rmovies.ui.adapter.SectionPagerAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initPagerAdapter()
    }

    private fun initPagerAdapter() {
        val sectionPagerAdapter = SectionPagerAdapter(this)
        val viewPager: ViewPager2 = binding.viewPager
        viewPager.adapter = sectionPagerAdapter
        val tabs: TabLayout = binding.tabLayout
        TabLayoutMediator(tabs, viewPager) { tabs, position ->
            tabs.text = resources.getString(
                TAB_TITLES[position]
            )
        }.attach()
    }

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.movies, R.string.tv_show)
    }
}