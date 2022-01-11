package com.latihan.rmovies.ui.tvshows

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.latihan.rmovies.model.DataRepository
import com.latihan.rmovies.model.entity.Item
import com.latihan.rmovies.model.entity.TvShowDetails
import com.latihan.rmovies.utils.DummyData
import junit.framework.TestCase
import kotlinx.coroutines.Dispatchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowsViewModelTest : TestCase() {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private var viewModel: TvShowsViewModel? = null

    @Mock
    private lateinit var dataRepository: DataRepository

    @Mock
    private lateinit var observer: Observer<List<Item>>

    @Mock
    private lateinit var observerDetail: Observer<TvShowDetails>

    @Before
    public override fun setUp() {
        viewModel = TvShowsViewModel(dataRepository)
    }

    @Test
    fun testGetShows() {
        val shows = MutableLiveData<List<Item>>()
        shows.value = DummyData.getDummyRemoteTvShows()
        lenient().`when`(dataRepository.getTvShows()).thenReturn(shows)
        viewModel?.shows?.observeForever(observer)
        verify(dataRepository).getTvShows()
        verify(observer).onChanged(shows.value)

    }

    @Test
    fun testGetDetailShow() {
        val shows = MutableLiveData<TvShowDetails>()
        shows.value = DummyData.getTvShowDetail()
        `when`(dataRepository.getTvShowDetail(shows.value!!.id.toString())).thenReturn(shows)
        viewModel?.getDetailShow(shows.value!!.id.toString())?.observeForever(observerDetail)
        verify(dataRepository).getTvShows()
        verify(observerDetail).onChanged(shows.value)

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

}