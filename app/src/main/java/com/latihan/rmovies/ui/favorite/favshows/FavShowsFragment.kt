package com.latihan.rmovies.ui.favorite.favshows

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.latihan.rmovies.R
import com.latihan.rmovies.databinding.FragmentFavShowsBinding


class FavShowsFragment : Fragment() {

    private var _binding: FragmentFavShowsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavShowsBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}