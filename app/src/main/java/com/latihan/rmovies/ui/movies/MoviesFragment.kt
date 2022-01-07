package com.latihan.rmovies.ui.movies

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.latihan.rmovies.databinding.FragmentMoviesBinding
import com.latihan.rmovies.ui.adapter.MoviesAdapter
import com.latihan.rmovies.utils.ViewModelFactory

class MoviesFragment : Fragment() {
    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getMovies()
    }

    private fun getMovies() {
        var adapter = MoviesAdapter()
        if (activity!=null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val moviesViewModel = ViewModelProviders.of(requireActivity(), factory)[MoviesViewModel::class.java]
            moviesViewModel.movies?.observe(viewLifecycleOwner, Observer {
                adapter.moviesAdapter(it)
                Log.d("rvMovies", it.size.toString())
            })
            with(binding?.rvMovies){
                this?.layoutManager = LinearLayoutManager(context)
                this?.adapter = adapter
                this?.setHasFixedSize(true)
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}