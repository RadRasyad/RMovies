package com.latihan.rmovies.ui.tvshows

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.latihan.rmovies.model.DataRepository
import com.latihan.rmovies.model.local.entity.MoviesEntity

import com.latihan.rmovies.utils.DummyData
import junit.framework.TestCase
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowsViewModelTest : TestCase() {

    /*
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private var viewModel: TvShowsViewModel? = null

    @Mock
    private lateinit var dataRepository: DataRepository

    @Mock
    private lateinit var observer: Observer<List<MoviesEntity>>

    @Mock
    private lateinit var observerDetail: Observer<TvShowDetails>

    @Before
    public override fun setUp() {
        viewModel = TvShowsViewModel(dataRepository)
    }

    @Test
    fun testGetShows() {
        val shows = MutableLiveData<List<MoviesEntity>>()
        shows.value = DummyData.getDummyRemoteTvShows()
        lenient().`when`(dataRepository.getTvShows()).thenReturn(shows)
        viewModel?.getListShows()?.observeForever(observer)
        Assert.assertNotNull(shows.value)
        Assert.assertEquals(20, shows.value!!.size)
        verify(dataRepository).getTvShows()
        verify(observer).onChanged(shows.value)
    }

    @Test
    fun testGetDetailShow() {
        val shows = MutableLiveData<TvShowDetails>()
        shows.value = DummyData.getTvShowDetail()
        `when`(dataRepository.getTvShowDetail(shows.value!!.id.toString())).thenReturn(shows)
        viewModel?.getDetailShow(shows.value?.id.toString())?.observeForever(observerDetail)
        verify(dataRepository).getTvShowDetail(shows.value?.id.toString())
        verify(observerDetail).onChanged(shows.value)

        assertNotNull(shows.value)
        assertEquals(
            shows.value!!.id,
            viewModel?.getDetailShow(shows.value!!.id.toString())?.value?.id
        )
        assertEquals(
            shows.value!!.name,
            viewModel?.getDetailShow(shows.value!!.id.toString())?.value?.name
        )
        assertEquals(
            shows.value!!.overview,
            viewModel?.getDetailShow(shows.value!!.id.toString())?.value?.overview
        )
        assertEquals(
            shows.value!!.firstAirDate,
            viewModel?.getDetailShow(shows.value!!.id.toString())?.value?.firstAirDate
        )
        assertEquals(
            shows.value!!.posterPath,
            viewModel?.getDetailShow(shows.value!!.id.toString())?.value?.posterPath
        )
        assertEquals(
            shows.value!!.backdropPath,
            viewModel?.getDetailShow(shows.value!!.id.toString())?.value?.backdropPath
        )
    }

     */

}