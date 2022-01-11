package com.latihan.rmovies.ui.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.latihan.rmovies.model.DataRepository
import com.latihan.rmovies.model.entity.Item
import com.latihan.rmovies.utils.DummyData
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MoviesViewModelTest {

    private var viewModel: MoviesViewModel? = null

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var dataRepository: DataRepository

    @Mock
    private lateinit var observer: Observer<List<Item>>

    @Mock
    private lateinit var observerDetail: Observer<Item>

    @Before
    fun setUp() {
        viewModel = MoviesViewModel(dataRepository)
    }

    @Test
    fun testGetMovies() {
        val movies = MutableLiveData<List<Item>>()
        movies.value = DummyData.getDummyRemoteMovies()
        lenient().`when`(dataRepository.getMovies()).thenReturn(movies)
        viewModel?.movies?.observeForever(observer)
        verify(dataRepository).getMovies()
        verify(observer).onChanged(movies.value)
    }

    @Test
    fun testGetDetailMovie() {
        val movies = MutableLiveData<Item>()
        movies.value = DummyData.getDummyRemoteMovies()[0]

        `when`(dataRepository.getMovieDetail(movies.value!!.id.toString())).thenReturn(movies)
        viewModel?.getDetailMovie(movies.value!!.id.toString())?.observeForever(observerDetail)
        verify(dataRepository).getMovies()
        verify(observerDetail).onChanged(movies.value)

        assertEquals(
            movies.value!!.id,
            viewModel?.getDetailMovie(movies.value!!.id.toString())?.value?.id
        )
        assertEquals(
            movies.value!!.title,
            viewModel?.getDetailMovie(movies.value!!.id.toString())?.value?.title
        )
        assertEquals(
            movies.value!!.overview,
            viewModel?.getDetailMovie(movies.value!!.id.toString())?.value?.overview
        )
        assertEquals(
            movies.value!!.releasedDate,
            viewModel?.getDetailMovie(movies.value!!.id.toString())?.value?.releasedDate
        )
        assertEquals(
            movies.value!!.posterPath,
            viewModel?.getDetailMovie(movies.value!!.id.toString())?.value?.posterPath
        )
        assertEquals(
            movies.value!!.backdropPath,
            viewModel?.getDetailMovie(movies.value!!.id.toString())?.value?.backdropPath
        )
    }
}