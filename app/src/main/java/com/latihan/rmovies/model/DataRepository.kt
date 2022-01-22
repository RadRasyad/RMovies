package com.latihan.rmovies.model

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.latihan.rmovies.model.local.LocalDataSource
import com.latihan.rmovies.model.local.entity.MoviesEntity
import com.latihan.rmovies.model.local.entity.TvShowsEntity
import com.latihan.rmovies.model.remote.ApiResponse
import com.latihan.rmovies.model.remote.DataSource
import com.latihan.rmovies.model.remote.RemoteRepository
import com.latihan.rmovies.model.remote.response.Movies
import com.latihan.rmovies.model.remote.response.TvShows
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

            override fun shouldFetch(data: PagedList<MoviesEntity>?): Boolean {
                var state = true
                if (data == null || data.isEmpty()) {
                    state = true
                }
                else if (!data.isEmpty()) {
                    state = false
                }

                return state
            }



            override fun createCall(): LiveData<ApiResponse<List<Movies>>> = remoteRepository.getMovies()

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
                        favoriteMovies = false
                    )
                    movieList.add(movie)
                }
                localDataSource.insertMovies(movieList)
            }
        }.asLiveData()

    }

    override fun getTvShows(): LiveData<Resource<PagedList<TvShowsEntity>>> {
        return object : NetworkBoundResource<PagedList<TvShowsEntity>, List<TvShows>>(appExecutors) {
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

            override fun createCall(): LiveData<ApiResponse<List<TvShows>>> =
                remoteRepository.getTvShows()

            override fun saveCallResult(data: List<TvShows>) {
                var showList = ArrayList<TvShowsEntity>()
                for(response in data) {
                    val show = TvShowsEntity(
                        response.id,
                        response.name,
                        response.overview,
                        response.firstAirDate,
                        response.voteAverage,
                        response.posterPath,
                        response.backdropPath,
                        favoriteShow = false
                    )
                    showList.add(show)
                }
                localDataSource.insertShows(showList)
            }
        }.asLiveData()
    }

    override fun getMovieDetail(movieId: String): LiveData<Resource<MoviesEntity>> {
        return object : NetworkBoundResource<MoviesEntity, List<Movies>>(appExecutors) {
            override fun loadFromDB(): LiveData<MoviesEntity> =
                localDataSource.getMovieDetail(movieId)

            override fun shouldFetch(data: MoviesEntity?): Boolean =
                data == null

            override fun createCall(): LiveData<ApiResponse<List<Movies>>> =
                remoteRepository.getDetailMovie(movieId)

            override fun saveCallResult(data: List<Movies>) {
                lateinit var moviesEntity: MoviesEntity
                for (response in data) {
                    moviesEntity = MoviesEntity(
                        response.id,
                        response.title,
                        response.overview,
                        response.releasedDate,
                        response.voteAverage,
                        response.posterPath,
                        response.backdropPath,
                        favoriteMovies = false
                    )
                }
                localDataSource.updateMovies(moviesEntity)
            }

        }.asLiveData()
    }

    override fun getTvShowDetail(tvShowId: String): LiveData<Resource<TvShowsEntity>> {
        return object : NetworkBoundResource<TvShowsEntity, List<TvShows>>(appExecutors) {
            override fun loadFromDB(): LiveData<TvShowsEntity> =
                localDataSource.getShowDetail(tvShowId)

            override fun shouldFetch(data: TvShowsEntity?): Boolean =
                data == null

            override fun createCall(): LiveData<ApiResponse<List<TvShows>>> =
                remoteRepository.getDetailShow(tvShowId)

            override fun saveCallResult(data: List<TvShows>) {
                lateinit var showsEntity: TvShowsEntity
                for (response in data) {
                    showsEntity = TvShowsEntity(
                        response.id,
                        response.name,
                        response.firstAirDate,
                        response.overview,
                        response.voteAverage,
                        response.posterPath,
                        response.backdropPath,
                        favoriteShow = false
                    )
                }
                localDataSource.updateShows(showsEntity)
            }

        }.asLiveData()
    }

    fun setFavMovies(moviesEntity: MoviesEntity, state: Boolean) =
        appExecutors.diskIO().execute {
            localDataSource.setFavMovies(moviesEntity, state)
        }

    fun setFavShows(showsEntity: TvShowsEntity, state: Boolean) {
        return appExecutors.diskIO().execute{
            localDataSource.setFavShows(showsEntity, state)
        }
    }

    fun getFavMovies(sort: String): LiveData<PagedList<MoviesEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(10)
            .setPageSize(10)
            .build()
        return LivePagedListBuilder(localDataSource.getFavMovies(sort), config).build()
    }

    fun getFavShows(sort: String): LiveData<PagedList<TvShowsEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(10)
            .setPageSize(10)
            .build()
        return LivePagedListBuilder(localDataSource.getFavShows(sort), config).build()
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