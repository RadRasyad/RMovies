package com.latihan.rmovies.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.latihan.rmovies.R
import com.latihan.rmovies.databinding.ActivityDetailBinding
import com.latihan.rmovies.model.local.entity.FavoriteMoviesEntity
import com.latihan.rmovies.model.local.entity.FavoriteTvShowsEntity
import com.latihan.rmovies.model.local.entity.MoviesEntity
import com.latihan.rmovies.model.local.entity.TvShowsEntity
import com.latihan.rmovies.ui.favorite.FavoriteViewModel
import com.latihan.rmovies.ui.movies.MoviesViewModel
import com.latihan.rmovies.ui.tvshows.TvShowsViewModel
import com.latihan.rmovies.utils.ViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    private lateinit var moviesViewModel: MoviesViewModel
    private lateinit var showsViewModel: TvShowsViewModel
    private lateinit var favoriteViewModel: FavoriteViewModel

    private var isFavorite: Boolean = false

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
            val idFavMovie = extras.getString(EXTRA_FAV_MOVIE)
            val idFavShow = extras.getString(EXTRA_FAV_SHOW)
            if (idMovie != null) {
                getDetailMovie(idMovie)
            } else if (idShow != null) {
                getDetailShow(idShow)
            } else if (idFavMovie != null) {
                getDetailFavMovie(idFavMovie)
            } else if (idFavShow != null) {
                getDetailFavShow(idFavShow)
                Log.d("data from", idFavShow)
            }

        }

    }

    private fun getDetailMovie(movie: String) {

        val factory = ViewModelFactory.getInstance(this)
        moviesViewModel = ViewModelProvider(this, factory!!)[MoviesViewModel::class.java]

        moviesViewModel.getDetailMovie(movie).observe(this, {
            progressBar(true)
            if (it.data != null)
                loadDataMovie(it.data)
            progressBar(false)
        })

    }

    private fun getDetailShow(shows: String) {

        val factory = ViewModelFactory.getInstance(this)
        showsViewModel = ViewModelProvider(this, factory!!)[TvShowsViewModel::class.java]

        showsViewModel.getDetailShow(shows).observe(this, {
            progressBar(true)
            loadDataShow(it.data)
            progressBar(false)
        })
    }

    private fun getDetailFavMovie(movie: String) {
        val factory = ViewModelFactory.getInstance(this)
        favoriteViewModel = ViewModelProvider(this, factory!!)[FavoriteViewModel::class.java]

        favoriteViewModel.getFavMDetail(movie).observe(this, {
            progressBar(true)
            if (it != null)
                loadDataFavMovie(it)
            progressBar(false)
        })

    }

    private fun getDetailFavShow(shows: String) {
        val factory = ViewModelFactory.getInstance(this)
        favoriteViewModel = ViewModelProvider(this, factory!!)[FavoriteViewModel::class.java]

        favoriteViewModel.getFavSDetail(shows).observe(this, {
            progressBar(true)
            loadDataFavShow(it)
            progressBar(false)
        })
    }

    private fun loadDataMovie(movie: MoviesEntity?) {
        if (movie != null) {
            checkFavMovies(movie.id)
            binding.apply {
                mtitleValue.text = movie.title ?: "-"
                mreleaseValue.text = movie.releasedDate ?: "-"
                mstarValue.text = movie.voteAverage.toString()
                moverviewValue.text = movie.overview ?: "No overview yet"
                if (movie.backdropPath == null) {
                    mbackdropPoster.loadImage(imgUrl + "${movie.posterPath}")
                }
                mbackdropPoster.loadImage(imgUrl + "${movie.backdropPath}")
                mivPoster.loadImage(imgUrl + "${movie.posterPath}")

                fabFavorite.setOnClickListener {
                    setFavoriteMovie(movie, isFavorite)
                    checkFavMovies(movie.id)
                }
            }
        }

    }

    private fun loadDataShow(shows: TvShowsEntity?) {
        if (shows != null) {
            checkFavShows(shows.id)
            binding.apply {
                mtitleValue.text = shows.name ?: "-"
                mreleaseValue.text = shows.firstAirDate ?: "-"
                mstarValue.text = shows.voteAverage.toString()
                moverviewValue.text = shows.overview ?: "No overview yet"
                if (shows.backdropPath != null) {
                    binding.mbackdropPoster.loadImage(imgUrl + "${shows.backdropPath}")
                } else {
                    binding.mbackdropPoster.loadImage(imgUrl + "${shows.posterPath}")
                }
                binding.mivPoster.loadImage(imgUrl + "${shows.posterPath}")
                fabFavorite.setOnClickListener {
                    setFavoriteShow(shows, isFavorite)
                    checkFavShows(shows.id)
                }

            }
        }

    }

    private fun loadDataFavMovie(movie: FavoriteMoviesEntity?) {
        if (movie != null) {
            checkFavMovies(movie.id)
            binding.apply {
                mtitleValue.text = movie.title ?: "-"
                mreleaseValue.text = movie.releasedDate ?: "-"
                mstarValue.text = movie.voteAverage.toString()
                moverviewValue.text = movie.overview ?: "No overview yet"
                if (movie.backdropPath == null) {
                    mbackdropPoster.loadImage(imgUrl + "${movie.posterPath}")
                }
                mbackdropPoster.loadImage(imgUrl + "${movie.backdropPath}")
                mivPoster.loadImage(imgUrl + "${movie.posterPath}")

                fabFavorite.setOnClickListener {
                    setFavMovie(movie, isFavorite)
                    checkFavMovies(movie.id)
                }
            }
        }
    }

    private fun loadDataFavShow(shows: FavoriteTvShowsEntity?) {
        if (shows != null) {
            checkFavShows(shows.id)
            binding.apply {
                mtitleValue.text = shows.name ?: "-"
                mreleaseValue.text = shows.firstAirDate ?: "-"
                mstarValue.text = shows.voteAverage.toString()
                moverviewValue.text = shows.overview ?: "No overview yet"
                if (shows.backdropPath != null) {
                    binding.mbackdropPoster.loadImage(imgUrl + "${shows.backdropPath}")
                } else {
                    binding.mbackdropPoster.loadImage(imgUrl + "${shows.posterPath}")
                }
                binding.mivPoster.loadImage(imgUrl + "${shows.posterPath}")
                fabFavorite.setOnClickListener {
                    setFavShow(shows, isFavorite)
                    checkFavShows(shows.id)
                }

            }
        }

    }

    private fun checkFavMovies(id: Int) {
        val factory = ViewModelFactory.getInstance(this)
        moviesViewModel = ViewModelProvider(this, factory!!)[MoviesViewModel::class.java]

        CoroutineScope(Dispatchers.IO).launch {
            val found = moviesViewModel.checkFavMovies(id)
            if (found > 0) {
                isFavorite = true
                setStatusFavorite(isFavorite)
            } else {
                isFavorite = false
                setStatusFavorite(isFavorite)
            }
        }

    }

    private fun setFavoriteMovie(movie: MoviesEntity, state: Boolean) {
        val factory = ViewModelFactory.getInstance(this)
        moviesViewModel = ViewModelProvider(this, factory!!)[MoviesViewModel::class.java]
        if (!state) {
            moviesViewModel.setFavMovies(movie)
            Toast.makeText(this, "Favorited", LENGTH_SHORT).show()
        } else if (state) {
            moviesViewModel.deleteFavMovies(movie)
            Toast.makeText(this, "Unfavorite", LENGTH_SHORT).show()
        }
    }

    private fun setFavMovie(movie: FavoriteMoviesEntity, state: Boolean) {
        val factory = ViewModelFactory.getInstance(this)
        favoriteViewModel = ViewModelProvider(this, factory!!)[FavoriteViewModel::class.java]
        if (state) {
            favoriteViewModel.delFavMovie(movie)
            finish()
            Toast.makeText(this, "Unfavorite", LENGTH_SHORT).show()
        }
    }

    private fun checkFavShows(id: Int) {
        val factory = ViewModelFactory.getInstance(this)
        showsViewModel = ViewModelProvider(this, factory!!)[TvShowsViewModel::class.java]

        CoroutineScope(Dispatchers.IO).launch {
            val found = showsViewModel.checkFavShow(id)
            if (found > 0) {
                isFavorite = true
                setStatusFavorite(isFavorite)
            } else {
                isFavorite = false
                setStatusFavorite(isFavorite)
            }
        }

    }

    private fun setFavoriteShow(shows: TvShowsEntity, state: Boolean) {
        val factory = ViewModelFactory.getInstance(this)
        showsViewModel = ViewModelProvider(this, factory!!)[TvShowsViewModel::class.java]
        if (!state) {
            showsViewModel.setFavShow(shows)
            Toast.makeText(this, "Favorited", LENGTH_SHORT).show()
        } else if (state) {
            showsViewModel.deleteFavShow(shows)
            Toast.makeText(this, "Unfavorite", LENGTH_SHORT).show()
        }
    }

    private fun setFavShow(shows: FavoriteTvShowsEntity, state: Boolean) {
        val factory = ViewModelFactory.getInstance(this)
        favoriteViewModel = ViewModelProvider(this, factory!!)[FavoriteViewModel::class.java]
        if (state) {
            favoriteViewModel.delFavShow(shows)
            finish()
            Toast.makeText(this, "Unfavorite", LENGTH_SHORT).show()
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
        if (isFavorite == true) {
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
        const val imgUrl = "https://image.tmdb.org/t/p/w500"

        const val EXTRA_MOVIE = "extra_movie"
        const val EXTRA_SHOW = "extra_show"
        const val EXTRA_FAV_MOVIE = "extra_fav_movie"
        const val EXTRA_FAV_SHOW = "extra_fav_show"
    }

}