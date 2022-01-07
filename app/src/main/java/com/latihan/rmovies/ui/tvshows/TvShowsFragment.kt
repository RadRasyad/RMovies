package com.latihan.rmovies.ui.tvshows

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.latihan.rmovies.R
import com.latihan.rmovies.databinding.FragmentTvShowsBinding


class TvShowsFragment : Fragment() {
    private lateinit var binding: FragmentTvShowsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTvShowsBinding.inflate(layoutInflater)
        return binding.root
    }

}