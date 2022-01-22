package com.latihan.rmovies.ui.tvshows

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.latihan.rmovies.databinding.FragmentTvShowsBinding
import com.latihan.rmovies.ui.adapter.TvShowsAdapter
import com.latihan.rmovies.utils.ViewModelFactory
import com.latihan.rmovies.vo.Status

class TvShowsFragment : Fragment() {
    private var _binding: FragmentTvShowsBinding? = null
    private val binding get() = _binding
    private val tAdapter = TvShowsAdapter()

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

        progressBar(true)
        val factory = ViewModelFactory.getInstance(requireActivity())
        val tvShowsViewModel =
            ViewModelProvider(requireActivity(), factory!!)[TvShowsViewModel::class.java]

        tvShowsViewModel.getListShows().observe(viewLifecycleOwner, Observer {

            if (it != null) {
                when (it.status) {
                    Status.LOADING -> progressBar(true)
                    Status.SUCCESS -> {
                        Log.d("fData", it.data?.size.toString())
                        tAdapter.submitList(it.data)
                        progressBar(false)
                    }
                    Status.ERROR -> {
                        progressBar(false)
                        Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
        with(binding?.rvShows) {
            this?.layoutManager = LinearLayoutManager(context)
            this?.adapter = tAdapter
            this?.setHasFixedSize(true)
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