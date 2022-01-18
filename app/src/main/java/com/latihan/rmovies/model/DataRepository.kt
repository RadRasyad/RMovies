package com.latihan.rmovies.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.latihan.rmovies.model.local.LocalDataSource
import com.latihan.rmovies.model.local.entity.MoviesEntity
import com.latihan.rmovies.model.local.entity.TvShowsEntity
import com.latihan.rmovies.model.remote.ApiResponse
import com.latihan.rmovies.model.remote.DataSource
import com.latihan.rmovies.model.remote.RemoteRepository
import com.latihan.rmovies.model.remote.response.Movies
import com.latihan.rmovies.utils.AppExecutors
import com.latihan.rmovies.vo.Resource

class DataRepository(private val remoteRepository: RemoteRepository, private val localDataSource: LocalDataSource, private val appExecutors: AppExecutors): DataSource {

    override fun getMovies(): LiveData<Resource<PagedList<MoviesEntity>>> {
        return object : NetworkBoundResource<PagedList<MoviesEntity>, List<Movies>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<MoviesEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(10)
                    .setPageSize(10)
                    .build()
                return LivePagedListBuilder(localDataSource.getMovies(), config).build()
            }

            override fun shouldFetch(data: PagedList<MoviesEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<Movies>>> {
                Log.d("datarepo", remoteRepository.getMovies().toString())
                return remoteRepository.getMovies()
            }

            override fun saveCallResult(data: List<Movies>) {
                val movieList = ArrayList<MoviesEntity>()
                for(response in data) {
                    val movie = MoviesEntity(
                        response.id,
                        response.title,
                        response.overview,
                        response.releasedDate,
                        response.voteAverage,
                        response.posterPath,
                        response.backdropPath,
                    )
                    movieList.add(movie)
                }
                localDataSource.updateMovies(movieList)
            }
        }.asLiveData()

    }

    override fun getTvShows(): LiveData<Resource<PagedList<TvShowsEntity>>> {
        return object : NetworkBoundResource<PagedList<TvShowsEntity>, List<TvShowsEntity>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<TvShowsEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(10)
                    .setPageSize(10)
                    .build()
                return LivePagedListBuilder(localDataSource.getShows(), config).build()
            }

            override fun shouldFetch(data: PagedList<TvShowsEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<TvShowsEntity>>> =
                remoteRepository.getTvShows()

            override fun saveCallResult(data: List<TvShowsEntity>) {
                lateinit var showList: TvShowsEntity
                for(response in data) {
                    showList = TvShowsEntity(
                        response.id,
                        response.name,
                        response.overview,
                        response.firstAirDate,
                        response.voteAverage,
                        response.posterPath,
                        response.backdropPath,
                    )
                }
                localDataSource.updateShows(showList)
            }
        }.asLiveData()
    }

    override fun getMovieDetail(movieId: String): LiveData<MoviesEntity> {
        val detailMovie = MutableLiveData<MoviesEntity>()
        remoteRepository.getDetailMovie(movieId, object: RemoteRepository.GetDetailMovieCallback{
            override fun onResponse(detailMovieResponse: MoviesEntity) {
                detailMovie.postValue(detailMovieResponse)
            }

        })
        return detailMovie
    }

    override fun getTvShowDetail(tvShowId: String): LiveData<TvShowsEntity> {
        val detailShow = MutableLiveData<TvShowsEntity>()
        remoteRepository.getDetailShow(tvShowId, object: RemoteRepository.GetDetailShowCallback{
            override fun onResponse(detailShowResponse: TvShowsEntity) {
                detailShow.postValue(detailShowResponse)
            }

        })
        return detailShow
    }

    companion object {
        private var instance: DataRepository? = null

        fun getInstance(remoteRepository: RemoteRepository, localDataSource: LocalDataSource, appExecutors: AppExecutors): DataRepository {

            synchronized(DataRepository::class.java) {
                if (instance == null) {
                    instance = DataRepository(remoteRepository, localDataSource, appExecutors)
                }
            }
            return instance!!
        }
    }

}