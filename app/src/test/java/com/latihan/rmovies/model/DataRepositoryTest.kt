package com.latihan.rmovies.model

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import com.latihan.rmovies.model.remote.RemoteRepository
import com.latihan.rmovies.repository.FakeDataRepository
import com.latihan.rmovies.utils.DummyData
import com.latihan.rmovies.utils.LiveDataTestUtil
import junit.framework.TestCase
import org.junit.Rule
import org.mockito.Mockito.*

class DataRepositoryTest : TestCase() {

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


    fun testGetMovies() {
        //doAnswer { invocation ->
        //    (invocation.arguments[0] as RemoteRepository)

        //}

        val moviesEntity = LiveDataTestUtil.getValue(RemoteRepository.getInstance().getMovies())
        verify(remoteRepository).getMovies()
        assertNotNull(moviesEntity)
        assertEquals(movieList.size.toLong(), moviesEntity.size.toLong())

    }

    fun testGetTvShows() {}

    fun testGetMovieDetail() {}

    fun testGetTvShowDetail() {}
}