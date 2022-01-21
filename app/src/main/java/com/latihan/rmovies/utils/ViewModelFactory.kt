package com.latihan.rmovies.utils

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.latihan.rmovies.di.Injection
import com.latihan.rmovies.model.DataRepository
import com.latihan.rmovies.ui.favorite.FavoriteViewModel
import com.latihan.rmovies.ui.movies.MoviesViewModel
import com.latihan.rmovies.ui.tvshows.TvShowsViewModel

class ViewModelFactory(private val dataRepository: DataRepository) :
    ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory? {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    if (INSTANCE == null)
                        INSTANCE = ViewModelFactory(Injection.moviesRepository(context))
                }
            }
            return INSTANCE
        }
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MoviesViewModel::class.java) -> MoviesViewModel(
                dataRepository
            ) as T

            modelClass.isAssignableFrom(TvShowsViewModel::class.java) -> TvShowsViewModel(
                dataRepository
            ) as T

            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> FavoriteViewModel(
                dataRepository
            ) as T

            else -> throw IllegalArgumentException("Unknown ViewModel: " + modelClass.name)
        }
    }

}