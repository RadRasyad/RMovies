package com.latihan.rmovies.ui.favorite.favshows

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.chip.Chip
import com.latihan.rmovies.databinding.FragmentFavShowsBinding
import com.latihan.rmovies.ui.adapter.FavTvShowsAdapter
import com.latihan.rmovies.ui.favorite.FavoriteViewModel
import com.latihan.rmovies.utils.SortUtils
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

        getFavShow(SortUtils.DEFAULT)
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

            getFavShow(sort)
        }

    }

    private fun getFavShow(sort: String) {

        val fsAdapter = FavTvShowsAdapter()
        val factory = ViewModelFactory.getInstance(requireActivity())
        favViewModel = ViewModelProvider(requireActivity(), factory!!)[FavoriteViewModel::class.java]
        favViewModel.getFavShow(sort).observe(viewLifecycleOwner, {
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