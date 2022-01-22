package com.latihan.rmovies.ui.favorite.favshows

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.latihan.rmovies.R
import com.latihan.rmovies.databinding.FragmentFavShowsBinding
import com.latihan.rmovies.ui.adapter.MoviesAdapter
import com.latihan.rmovies.ui.adapter.TvShowsAdapter
import com.latihan.rmovies.ui.favorite.FavoriteViewModel
import com.latihan.rmovies.utils.ViewModelFactory


class FavShowsFragment : Fragment() {

    private var _binding: FragmentFavShowsBinding? = null
    private val binding get() = _binding!!

    private lateinit var favViewModel: FavoriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavShowsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getFavShow()
    }

    private fun getFavShow() {

        val fsAdapter = TvShowsAdapter()
        val factory = ViewModelFactory.getInstance(requireActivity())
        favViewModel = ViewModelProvider(requireActivity(), factory!!)[FavoriteViewModel::class.java]
        favViewModel.getFavShow().observe(viewLifecycleOwner, Observer {
            if (it!=null) {
                fsAdapter.submitList(it)
                progressBar(false)
            }
            else progressBar(false)
        })

        with(binding.rvFavShows) {
            this.layoutManager = LinearLayoutManager(context)
            this.adapter = fsAdapter
            this.setHasFixedSize(true)
        }
    }

    private fun progressBar(state: Boolean) {
        if (!state) binding.progressBar.visibility = View.GONE
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}