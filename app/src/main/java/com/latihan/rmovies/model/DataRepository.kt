package com.latihan.rmovies.model

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.latihan.rmovies.model.local.LocalDataSource
import com.latihan.rmovies.model.local.entity.FavoriteMoviesEntity
import com.latihan.rmovies.model.local.entity.FavoriteTvShowsEntity
import com.latihan.rmovies.model.local.entity.MoviesEntity
import com.latihan.rmovies.model.local.entity.TvShowsEntity
import com.latihan.rmovies.model.remote.ApiResponse
import com.latihan.rmovies.model.remote.DataSource
import com.latihan.rmovies.model.remote.RemoteRepository
import com.latihan.rmovies.model.remote.response.Movies
import com.latihan.rmovies.model.remote.response.TvShows
import com.latihan.rmovies.utils.AppExecutors
import com.latihan.rmovies.vo.Resource

class DataRepository(
    private val remoteRepository: RemoteRepository,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : DataSource {

    override fun getMovies(): LiveData<Resource<PagedList<MoviesEntity>>> {
        return object : NetworkBoundResource<PagedList<MoviesEntity>, List<Movies>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<MoviesEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getMovies(), config).build()
            }

            override fun shouldFetch(data: PagedList<MoviesEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<Movies>>> =
                remoteRepository.getMovies()

            override fun saveCallResult(data: List<Movies>) {
                val movieList = ArrayList<MoviesEntity>()
                for (response in data) {
                    val movie = MoviesEntity(
                        response.id,
                        response.title,
                        response.overview,
                        response.releasedDate,
                        response.voteAverage,
                        response.posterPath,
                        response.backdropPath
                    )
                    movieList.add(movie)
                }
                localDataSource.insertMovies(movieList)
            }
        }.asLiveData()

    }

    override fun getTvShows(): LiveData<Resource<PagedList<TvShowsEntity>>> {
        return object :
            NetworkBoundResource<PagedList<TvShowsEntity>, List<TvShows>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<TvShowsEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getShows(), config).build()
            }

            override fun shouldFetch(data: PagedList<TvShowsEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<TvShows>>> =
                remoteRepository.getTvShows()

            override fun saveCallResult(data: List<TvShows>) {
                val showList = ArrayList<TvShowsEntity>()
                for (response in data) {
                    val show = TvShowsEntity(
                        response.id,
                        response.name,
                        response.overview,
                        response.firstAirDate,
                        response.voteAverage,
                        response.posterPath,
                        response.backdropPath
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
                        response.backdropPath
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
                        response.backdropPath
                    )
                }
                localDataSource.updateShows(showsEntity)
            }

        }.asLiveData()
    }

    fun checkFavMovies(id: Int) = localDataSource.checkFavMovies(id)

    fun setFavMovies(data: MoviesEntity) {

            val favList = FavoriteMoviesEntity(
                data.id,
                data.title,
                data.releasedDate,
                data.overview,
                data.voteAverage,
                data.posterPath,
                data.backdropPath
            )
        return appExecutors.diskIO().execute {
            localDataSource.insertFavMovies(favList)
        }
    }

    fun delFavMovies(data: MoviesEntity) {
        val favList = FavoriteMoviesEntity(
            data.id,
            data.title,
            data.releasedDate,
            data.overview,
            data.voteAverage,
            data.posterPath,
            data.backdropPath
        )
        return appExecutors.diskIO().execute {
            localDataSource.deleteFavMovies(favList)
        }
    }

    fun delFavM(data: FavoriteMoviesEntity) {
        return appExecutors.diskIO().execute {
            localDataSource.deleteFavMovies(data)
        }
    }

    fun getFavMovies(sort: String): LiveData<PagedList<FavoriteMoviesEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavMovies(sort), config).build()
    }

    fun getFavMDetail(id: String): LiveData<FavoriteMoviesEntity> =
        localDataSource.getFavMDetail(id)


    fun checkFavShow(id: Int) = localDataSource.checkFavShow(id)

    fun setFavShow(data: TvShowsEntity) {

        val favList = FavoriteTvShowsEntity(
            data.id,
            data.name,
            data.firstAirDate,
            data.overview,
            data.voteAverage,
            data.posterPath,
            data.backdropPath,
        )
        return appExecutors.diskIO().execute {
            localDataSource.insertFavShow(favList)
        }
    }

    fun delFavShow(data: TvShowsEntity) {
        val favList = FavoriteTvShowsEntity(
            data.id,
            data.name,
            data.firstAirDate,
            data.overview,
            data.voteAverage,
            data.posterPath,
            data.backdropPath,

        )
        return appExecutors.diskIO().execute {
            localDataSource.deleteFavShow(favList)
        }
    }

    fun delFavS(data: FavoriteTvShowsEntity) {
        return appExecutors.diskIO().execute {
            localDataSource.deleteFavShow(data)
        }
    }

    fun getFavShows(sort: String): LiveData<PagedList<FavoriteTvShowsEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavShow(sort), config).build()
    }

    fun getFavSDetail(id: String): LiveData<FavoriteTvShowsEntity> =
        localDataSource.getFavSDetail(id)

    companion object {
        private var instance: DataRepository? = null

        fun getInstance(
            remoteRepository: RemoteRepository,
            localDataSource: LocalDataSource,
            appExecutors: AppExecutors
        ): DataRepository {

            synchronized(DataRepository::class.java) {
                if (instance == null) {
                    instance = DataRepository(remoteRepository, localDataSource, appExecutors)
                }
            }
            return instance!!
        }
    }

}