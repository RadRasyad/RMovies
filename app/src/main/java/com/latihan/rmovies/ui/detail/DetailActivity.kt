package com.latihan.rmovies.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.latihan.rmovies.R
import com.latihan.rmovies.databinding.ActivityDetailBinding
import com.latihan.rmovies.model.local.entity.MoviesEntity
import com.latihan.rmovies.model.local.entity.TvShowsEntity
import com.latihan.rmovies.ui.movies.MoviesViewModel
import com.latihan.rmovies.ui.tvshows.TvShowsViewModel
import com.latihan.rmovies.utils.ViewModelFactory

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var moviesViewModel: MoviesViewModel
    private lateinit var showsViewModel: TvShowsViewModel
    private var dataType: String = "movie"

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
                dataType = "movie"
            } else {
                if (idShow != null) {
                    getDetailShow(idShow)
                    dataType = "show"
                }
            }
        }

    }

    private fun getDetailMovie(movie: String) {

        val factory = ViewModelFactory.getInstance(this)
        moviesViewModel = ViewModelProviders.of(this, factory)[MoviesViewModel::class.java]

        moviesViewModel.getDetailMovie(movie).observe(this, {
            progressBar(true)
            loadDataMovie(it.data)
            progressBar(false)
        })

    }

    private fun getDetailShow(shows: String) {

        val factory = ViewModelFactory.getInstance(this)
        showsViewModel = ViewModelProviders.of(this, factory)[TvShowsViewModel::class.java]

        showsViewModel.getDetailShow(shows).observe(this, {
            progressBar(true)
            loadDataShow(it.data)
            progressBar(false)
        })
    }

    private fun loadDataMovie(movie: MoviesEntity?) {

        binding.apply {
            mtitleValue.text = movie?.title ?: "-"
            mreleaseValue.text = movie?.releasedDate ?: "-"
            mstarValue.text = movie?.voteAverage.toString()
            moverviewValue.text = movie?.overview ?: "No overview yet"
            if (movie?.backdropPath == null) {
                binding.mbackdropPoster.loadImage("https://image.tmdb.org/t/p/w500${movie?.posterPath}")
            }
            binding.mbackdropPoster.loadImage("https://image.tmdb.org/t/p/w500${movie?.backdropPath}")
            binding.mivPoster.loadImage("https://image.tmdb.org/t/p/w500${movie?.posterPath}")
        }
        Log.d("first fav State", movie?.favoriteMovies.toString())

        binding.fabFavorite.setOnClickListener{
            if (movie != null) {
                setFavorite(dataType, movie)
            }
            Log.d("fav State", movie?.favoriteMovies.toString())
        }

    }

    private fun loadDataShow(shows: TvShowsEntity?) {

        binding.apply {
            mtitleValue.text = shows?.name ?: "-"
            mreleaseValue.text = shows?.firstAirDate ?: "-"
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

    private fun setFavorite(dataType: String, movie: MoviesEntity) {
        if (dataType == "movies") {
            if (movie.favoriteMovies == false) {
                moviesViewModel.setFavMovies(movie,true)
                Log.d("on Change", movie.favoriteMovies.toString())
                Toast.makeText(this, "Favorited", LENGTH_LONG).show()
            } else {
                moviesViewModel.setFavMovies(movie,false)
                Toast.makeText(this, "Unfavorite", LENGTH_LONG).show()
            }
        } else if (dataType == "shows") {
            lateinit var shows: TvShowsEntity
            if (shows.favoriteShow != true) {
                showsViewModel.setFavShows(shows)
                setStatusFavorite(shows.favoriteShow)
                Toast.makeText(this, "Favorited", LENGTH_LONG).show()
            } else {
                showsViewModel.setFavShows(shows)
                setStatusFavorite(shows.favoriteShow)
                Toast.makeText(this, "Unfavorite", LENGTH_LONG).show()
            }
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

    private fun setStatusFavorite(isFavorite: Boolean) {
        if (isFavorite) {
            binding.fabFavorite.setImageResource(R.drawable.ic_heart_fill)
        } else {
            binding.fabFavorite.setImageResource(R.drawable.ic_heart_add_line_)
        }
    }

    private fun ImageView.loadImage(url: String?) {
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