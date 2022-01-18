package com.latihan.rmovies.ui.movies

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.latihan.rmovies.databinding.FragmentMoviesBinding
import com.latihan.rmovies.ui.adapter.MoviesAdapter
import com.latihan.rmovies.utils.ViewModelFactory
import com.latihan.rmovies.vo.Status

class MoviesFragment : Fragment() {

    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getMovies()
        /*
        val factory = ViewModelFactory.getInstance(requireActivity())
        val moviesViewModel = ViewModelProviders.of(requireActivity(), factory)[MoviesViewModel::class.java]
        moviesViewModel.getData().observe(viewLifecycleOwner, Observer {
            val moviesAdapter = TestAdapter()
            if (it!=null) {
                moviesAdapter.add(it)
                progressBar(false)
            }
            with(binding.rvMovies){
                this.layoutManager = LinearLayoutManager(context)
                this.adapter = moviesAdapter
                this.setHasFixedSize(true)
            }
        })

         */



    }

    private fun getMovies() {
        progressBar(true)
        val mAdapter = MoviesAdapter()
        val factory = ViewModelFactory.getInstance(requireActivity())
        val moviesViewModel = ViewModelProviders.of(requireActivity(), factory)[MoviesViewModel::class.java]
        moviesViewModel.getListMovies().observe(viewLifecycleOwner, Observer {
            if (it!=null) {
                when(it.status) {
                    Status.LOADING -> progressBar(true)
                    Status.SUCCESS -> {
                        Log.d("fData", it.data?.size.toString())
                        mAdapter.submitList(it.data)
                        progressBar(false)
                    }
                    Status.ERROR -> {
                        progressBar(false)
                        Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }
            }

        })
        with(binding.rvMovies){
            this.layoutManager = LinearLayoutManager(context)
            this.adapter = mAdapter
            this.setHasFixedSize(true)
        }

    }

    private fun progressBar(state: Boolean) {
        if (!state) binding.progressBar.visibility = View.GONE
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}