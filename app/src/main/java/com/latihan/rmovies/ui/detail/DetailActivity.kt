package com.latihan.rmovies.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.latihan.rmovies.R
import com.latihan.rmovies.databinding.ActivityDetailBinding
import com.latihan.rmovies.model.local.entity.Item
import com.latihan.rmovies.model.local.entity.TvShowDetails
import com.latihan.rmovies.ui.movies.MoviesViewModel
import com.latihan.rmovies.ui.tvshows.TvShowsViewModel
import com.latihan.rmovies.utils.ViewModelFactory

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
        supportActionBar?.title = " "

        val extras = intent.extras

        if (extras != null) {
            val idMovie = extras.getString(EXTRA_MOVIE)
            val idShow = extras.getString(EXTRA_SHOW)
            if (idMovie != null) {
                getDetailMovie(idMovie)
            } else {
                if (idShow != null) {
                    getDetailShow(idShow)
                }
            }
        }
    }

    private fun getDetailMovie(movie: String) {

        val factory = ViewModelFactory.getInstance(this)
        val moviesViewModel = ViewModelProviders.of(this, factory)[MoviesViewModel::class.java]

        moviesViewModel.getDetailMovie(movie).observe(this, Observer {
            progressBar(true)
            loadDataMovie(it)
            progressBar(false)
        })
    }

    private fun getDetailShow(shows: String) {

        val factory = ViewModelFactory.getInstance(this)
        val showsViewModel = ViewModelProviders.of(this, factory)[TvShowsViewModel::class.java]

        showsViewModel.getDetailShow(shows).observe(this, Observer {
            progressBar(true)
            loadDataShow(it)
            progressBar(false)
        })
    }

    private fun loadDataMovie(movie: Item?) {

        binding.apply {
            mtitleValue.text = movie?.title
            mreleaseValue.text = movie?.releasedDate
            mstarValue.text = movie?.voteAverage.toString()
            moverviewValue.text = movie?.overview
            if (movie?.backdropPath == null) {
                binding.mbackdropPoster.loadImage("https://image.tmdb.org/t/p/w500${movie?.posterPath}")
            }
            binding.mbackdropPoster.loadImage("https://image.tmdb.org/t/p/w500${movie?.backdropPath}")
            binding.mivPoster.loadImage("https://image.tmdb.org/t/p/w500${movie?.posterPath}")
        }

    }

    private fun loadDataShow(shows: TvShowDetails?) {

        binding.apply {
            mtitleValue.text = shows?.name
            mreleaseValue.text = shows?.firstAirDate
            mstarValue.text = shows?.voteAverage.toString()
            moverviewValue.text = shows?.overview ?: "No overview yet"
            if (shows?.backdropPath != null) {
                binding.mbackdropPoster.loadImage("https://image.tmdb.org/t/p/w500${shows.backdropPath}")
            } else {
                binding.mbackdropPoster.loadImage("https://image.tmdb.org/t/p/w500${shows?.posterPath}")
            }
            binding.mivPoster.loadImage("https://image.tmdb.org/t/p/w500${shows?.posterPath}")
        }

    }

    private fun progressBar(state: Boolean) {
        if (!state) {
            binding.progressBar.visibility = View.GONE
            binding.detailView.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.VISIBLE
            binding.detailView.visibility = View.GONE
        }
    }


    fun ImageView.loadImage(url: String?) {
        Glide.with(this.context)
            .load(url)
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.ic_error)
            .centerCrop()
            .into(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {

            android.R.id.home -> {
                finish()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
        const val EXTRA_SHOW = "extra_show"
    }
}