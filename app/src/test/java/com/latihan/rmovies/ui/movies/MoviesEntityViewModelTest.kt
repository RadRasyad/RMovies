package com.latihan.rmovies.ui.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.latihan.rmovies.model.DataRepository
import com.latihan.rmovies.model.local.entity.MoviesEntity
import com.latihan.rmovies.utils.DummyData
import com.latihan.rmovies.vo.Resource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MoviesEntityViewModelTest {


    private var viewModel: MoviesViewModel? = null

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var dataRepository: DataRepository

    @Mock
    private lateinit var observerDetail: Observer<Resource<MoviesEntity>>

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<MoviesEntity>>>

    @Mock
    private lateinit var pagedList: PagedList<MoviesEntity>

    @Before
    fun setUp() {
        viewModel = MoviesViewModel(dataRepository)
    }

    @Test
    fun testGetMovies() {
        val movies = Resource.success(pagedList)
        `when`(movies.data?.size).thenReturn(20)

        val allMovies = MutableLiveData<Resource<PagedList<MoviesEntity>>>()
        allMovies.value = movies

        lenient().`when`(dataRepository.getMovies()).thenReturn(allMovies)
        val entity = viewModel?.getListMovies()?.value?.data
        verify(dataRepository).getMovies()

        assertNotNull(entity)
        assertEquals(20, entity?.size)

        viewModel?.getListMovies()?.observeForever(observer)
        verify(observer).onChanged(movies)
    }


    @Test
    fun testGetDetailMovie() {
        val expect = MutableLiveData<Resource<MoviesEntity>>()
        val dummyData = DummyData.getDummyRemoteMovies()[0]
        val id = DummyData.getDummyRemoteMovies()[0].id
        expect.value = Resource.success(dummyData)

        `when`(dataRepository.getMovieDetail(id.toString())).thenReturn(expect)
        viewModel?.getDetailMovie(id.toString())?.observeForever(observerDetail)

        verify(observerDetail).onChanged(expect.value)

        val actual = viewModel?.getDetailMovie(id.toString())

        assertEquals(expect.value, actual?.value)

    }


}