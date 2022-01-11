package com.latihan.rmovies.ui.tvshows

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.latihan.rmovies.databinding.FragmentTvShowsBinding
import com.latihan.rmovies.ui.adapter.TvShowsAdapter
import com.latihan.rmovies.utils.ViewModelFactory

class TvShowsFragment : Fragment() {
    private var _binding: FragmentTvShowsBinding? = null
    private val binding get() = _binding
    private val adapter = TvShowsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTvShowsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getShows()
    }

    private fun getShows() {

        if (activity != null) {
            progressBar(true)
            val factory = ViewModelFactory.getInstance(requireActivity())
            val tvShowsViewModel =
                ViewModelProviders.of(requireActivity(), factory)[TvShowsViewModel::class.java]
            tvShowsViewModel.shows.observe(viewLifecycleOwner, Observer {
                adapter.showsAdapter(it)
                progressBar(false)
            })
            with(binding?.rvShows) {
                this?.layoutManager = LinearLayoutManager(context)
                this?.adapter = adapter
                this?.setHasFixedSize(true)
            }
        }

    }

    private fun progressBar(state: Boolean) {
        if (!state) binding?.progressBar?.visibility = View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}