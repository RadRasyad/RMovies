package com.latihan.rmovies.model

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.latihan.rmovies.model.remote.RemoteRepository
import com.latihan.rmovies.repository.FakeDataRepository
import com.latihan.rmovies.utils.DummyData
import com.latihan.rmovies.utils.LiveDataTestUtil
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

class DataRepositoryTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remoteRepository = mock(RemoteRepository::class.java)
    private val dataRepositoryTest = FakeDataRepository(remoteRepository)

    private val movieList = DummyData.getDummyRemoteMovies()
    private val movieId = DummyData.getDummyRemoteMovies()[0].id.toString()
    private val tvShowsList = DummyData.getDummyRemoteTvShows()
    private val showId = DummyData.getDummyRemoteTvShows()[0].id.toString()
    private val tvShowDetails = DummyData.getTvShowDetail()

    private fun <T> anyOfGeneric(type: Class<T>): T = any(type)
    private fun <T> eqOfGeneric(obj: T): T = eq(obj)

    @Test
    fun testGetMovies() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteRepository.GetMoviesCallback).onResponse(movieList)
            null
        }.`when`(remoteRepository).getMovies(anyOfGeneric(RemoteRepository.GetMoviesCallback::class.java))

        val moviesEntity = LiveDataTestUtil.getValue(dataRepositoryTest.getMovies())
        verify(remoteRepository).getMovies(anyOfGeneric(RemoteRepository.GetMoviesCallback::class.java))
        assertNotNull(moviesEntity)
        assertEquals(movieList.size.toLong(), moviesEntity.size.toLong())

    }

    @Test
    fun testGetTvShows() {}

    @Test
    fun testGetMovieDetail() {}

    @Test
    fun testGetTvShowDetail() {}

}