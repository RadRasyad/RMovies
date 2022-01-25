package com.latihan.rmovies.model

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import com.latihan.rmovies.model.local.LocalDataSource
import com.latihan.rmovies.model.local.entity.FavoriteMoviesEntity
import com.latihan.rmovies.model.local.entity.FavoriteTvShowsEntity
import com.latihan.rmovies.model.local.entity.MoviesEntity
import com.latihan.rmovies.model.local.entity.TvShowsEntity
import com.latihan.rmovies.model.remote.RemoteRepository
import com.latihan.rmovies.repository.FakeDataRepository
import com.latihan.rmovies.utils.*
import com.nhaarman.mockitokotlin2.*
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import com.latihan.rmovies.vo.Resource
import org.mockito.Mockito.`when`

class DataRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val localRepository = mock(LocalDataSource::class.java)
    private val remoteRepository = mock(RemoteRepository::class.java)
    private val appExecutors = mock(AppExecutors::class.java)
    private val dataRepositoryTest = FakeDataRepository(remoteRepository, localRepository, appExecutors)

    private val movieList = DummyData.getDummyRemoteMovies()
    private val movieId = DummyData.getDummyRemoteMovies()[0].id.toString()
    private val movieDetails = DummyData.getMovieDetail()
    private val favMovies = DummyData.getDummyFavoriteMovies()
    private val favMId = DummyData.getDummyFavoriteMovies()[0].id.toString()
    private val tvShowsList = DummyData.getDummyRemoteTvShows()
    private val showId = DummyData.getDummyRemoteTvShows()[0].id.toString()
    private val tvShowDetails = DummyData.getTvShowDetail()
    private val favShow = DummyData.getDummyFavoriteShow()
    private val favSId = DummyData.getDummyFavoriteShow()[0].id.toString()

    @Test
    fun testGetMovies() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MoviesEntity>
        `when`(localRepository.getMovies()).thenReturn(dataSourceFactory)
        dataRepositoryTest.getMovies()

        val moviesEntity = Resource.success(PagedListUtil.mockPagedList(DummyData.getDummyRemoteMovies()))
        verify(localRepository).getMovies()
        assertNotNull(moviesEntity.data)
        assertEquals(movieList.size.toLong(), moviesEntity.data?.size?.toLong())

    }

    @Test
    fun testGetTvShows() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowsEntity>
        `when`(localRepository.getShows()).thenReturn(dataSourceFactory)
        dataRepositoryTest.getTvShows()

        val showsEntity = Resource.success(PagedListUtil.mockPagedList(DummyData.getDummyRemoteTvShows()))
        println(showsEntity)
        verify(localRepository).getShows()
        assertNotNull(showsEntity)
        assertEquals(tvShowsList.size.toLong(), showsEntity.data?.size?.toLong())

    }

    @Test
    fun testGetMovieDetail() {
        val detailEntity = MutableLiveData<MoviesEntity>()
        detailEntity.value = movieDetails
        `when`(localRepository.getMovieDetail(movieId)).thenReturn(detailEntity)

        val movie = LiveDataTestUtil.getValue(dataRepositoryTest.getMovieDetail(movieId))

        verify(localRepository).getMovieDetail(movieId)
        assertNotNull(detailEntity)
        assertEquals(movieDetails.title, movie.data?.title)
    }

    @Test
    fun testGetTvShowDetail() {

        val detailEntity = MutableLiveData<TvShowsEntity>()
        detailEntity.value = tvShowDetails
        `when`(localRepository.getShowDetail(showId)).thenReturn(detailEntity)

        val show = LiveDataTestUtil.getValue(dataRepositoryTest.getTvShowDetail(showId))

        verify(localRepository).getShowDetail(showId)
        assertNotNull(detailEntity)
        assertEquals(tvShowDetails.name, show.data?.name)
    }

    @Test
    fun testGetFavMovies() {
        val sort = SortUtils.DEFAULT
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, FavoriteMoviesEntity>
        `when`(localRepository.getFavMovies(sort)).thenReturn(dataSourceFactory)
        dataRepositoryTest.getFavMovies(sort)

        val movies = Resource.success(PagedListUtil.mockPagedList(DummyData.getDummyFavoriteMovies()))

        verify(localRepository).getFavMovies(sort)
        assertNotNull(movies)
        assertEquals(favMovies.size, movies.data?.size)

    }

    @Test
    fun testGetFavShow() {
        val sort = SortUtils.DEFAULT
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, FavoriteTvShowsEntity>
        `when`(localRepository.getFavShow(sort)).thenReturn(dataSourceFactory)
        dataRepositoryTest.getFavShows(sort)

        val show = Resource.success(PagedListUtil.mockPagedList(DummyData.getDummyFavoriteShow()))

        verify(localRepository).getFavShow(sort)
        assertNotNull(show)
        assertEquals(favShow.size, show.data?.size)
    }

    @Test
    fun testGetFavMoviesDetail() {
        val favMDetail = MutableLiveData<FavoriteMoviesEntity>()
        favMDetail.value = DummyData.getDummyFavoriteMovies()[0]
        `when`(localRepository.getFavMDetail(favMId)).thenReturn(favMDetail)

        val movies = LiveDataTestUtil.getValue(dataRepositoryTest.getFavMDetail(favMId))

        verify(localRepository).getFavMDetail(favMId)
        assertNotNull(favMDetail)
        assertEquals(favMDetail.value?.title, movies.title)
    }

    @Test
    fun testGetFavShowDetail() {
        val favSDetail = MutableLiveData<FavoriteTvShowsEntity>()
        favSDetail.value = DummyData.getDummyFavoriteShow()[0]
        `when`(localRepository.getFavSDetail(favSId)).thenReturn(favSDetail)

        val show = LiveDataTestUtil.getValue(dataRepositoryTest.getFavSDetail(favSId))

        verify(localRepository).getFavSDetail(favSId)
        assertNotNull(favSDetail)
        assertEquals(favSDetail.value?.name, show.name)
    }

}