package com.latihan.rmovies.ui.favorite.favmovies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.chip.Chip
import com.latihan.rmovies.databinding.FragmentFavMoviesBinding
import com.latihan.rmovies.ui.adapter.FavMoviesAdapter
import com.latihan.rmovies.ui.favorite.FavoriteViewModel
import com.latihan.rmovies.utils.SortUtils
import com.latihan.rmovies.utils.ViewModelFactory


class FavMoviesFragment : Fragment() {

    private var _binding: FragmentFavMoviesBinding? = null
    private val binding get() = _binding!!
    private lateinit var favViewModel: FavoriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavMoviesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getFavMovies(SortUtils.DEFAULT)
        sortList()
    }

    private fun sortList() {
        var sort = ""
        val cGroup = binding.chipGroup
        cGroup.setOnCheckedChangeListener { group, checkedId ->
            val title = group.findViewById<Chip>(checkedId).text
            sort = title.toString()
            when(sort) {

                "Vote Average" -> sort = SortUtils.VOTE
                "Name" -> sort = SortUtils.NAME
                "Default" -> sort = SortUtils.DEFAULT
            }

            getFavMovies(sort)
        }

    }

    private fun getFavMovies(sort: String) {

        progressBar(true)
        val fmAdapter = FavMoviesAdapter()
        val factory = ViewModelFactory.getInstance(requireActivity())
        favViewModel = ViewModelProvider(requireActivity(), factory!!)[FavoriteViewModel::class.java]
        favViewModel.getFavMovies(sort).observe(viewLifecycleOwner,{
            if (it!=null) {
                fmAdapter.submitList(it)
                progressBar(false)
            }
            else progressBar(false)
        })

        with(binding.rvFavMovies) {
            this.layoutManager = LinearLayoutManager(context)
            this.adapter = fmAdapter
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