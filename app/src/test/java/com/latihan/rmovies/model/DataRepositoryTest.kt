package com.latihan.rmovies.model

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.latihan.rmovies.model.remote.RemoteRepository
import com.latihan.rmovies.repository.FakeDataRepository
import com.latihan.rmovies.utils.DummyData
import com.latihan.rmovies.utils.LiveDataTestUtil
import com.nhaarman.mockitokotlin2.*
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock

class DataRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remoteRepository = mock(RemoteRepository::class.java)
    private val dataRepositoryTest = FakeDataRepository(remoteRepository)

    private val movieList = DummyData.getDummyRemoteMovies()
    private val movieId = DummyData.getDummyRemoteMovies()[0].id.toString()
    private val movieDetails = DummyData.getMovieDetail()
    private val tvShowsList = DummyData.getDummyRemoteTvShows()
    private val showId = DummyData.getDummyRemoteTvShows()[0].id.toString()
    private val tvShowDetails = DummyData.getTvShowDetail()

    @Test
    fun testGetMovies() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteRepository.GetMoviesCallback).onResponse(movieList)
            null
        }.`when`(remoteRepository)
            .getMovies(any())

        val moviesEntity = LiveDataTestUtil.getValue(dataRepositoryTest.getMovies())
        println(moviesEntity)
        verify(remoteRepository).getMovies(any())
        assertNotNull(moviesEntity)
        assertEquals(movieList.size.toLong(), moviesEntity.size.toLong())

    }

    @Test
    fun testGetTvShows() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteRepository.GetTvShowsCallback).onResponse(tvShowsList)
            null
        }.`when`(remoteRepository)
            .getTvShows(any())

        val showsEntity = LiveDataTestUtil.getValue(dataRepositoryTest.getTvShows())
        println(showsEntity)
        verify(remoteRepository).getTvShows(any())
        assertNotNull(showsEntity)
        assertEquals(tvShowsList.size.toLong(), showsEntity.size.toLong())

    }

    @Test
    fun testGetMovieDetail() {

        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteRepository.GetDetailMovieCallback).onResponse(
                movieDetails
            )
            null
        }.`when`(remoteRepository)
            .getDetailMovie(eq(movieId), any())

        val detailEntity =
            LiveDataTestUtil.getValue(dataRepositoryTest.getMovieDetail(movieId))
        println(detailEntity)
        verify(remoteRepository).getDetailMovie(
            eq(movieId), any()
        )
        assertNotNull(detailEntity)
        assertEquals(movieDetails.title, detailEntity.title)
    }

    @Test
    fun testGetTvShowDetail() {
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteRepository.GetDetailShowCallback).onResponse(
                tvShowDetails
            )
            null
        }.`when`(remoteRepository).getDetailShow(
            eq(showId),
            any()
        )
        val detailEntity = LiveDataTestUtil.getValue(dataRepositoryTest.getTvShowDetail(showId))
        println(detailEntity)
        verify(remoteRepository).getDetailShow(eq(showId), any())
        assertNotNull(detailEntity)
        assertEquals(tvShowDetails.name, detailEntity.name)
    }

}