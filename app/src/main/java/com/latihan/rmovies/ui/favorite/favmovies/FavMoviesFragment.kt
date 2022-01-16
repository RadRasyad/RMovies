package com.latihan.rmovies.ui.favorite.favmovies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.latihan.rmovies.R
import com.latihan.rmovies.databinding.FragmentFavMoviesBinding


class FavMoviesFragment : Fragment() {

    private var _binding: FragmentFavMoviesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavMoviesBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}