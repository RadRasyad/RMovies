package com.latihan.rmovies.ui.favorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.latihan.rmovies.model.DataRepository
import com.latihan.rmovies.model.local.entity.FavoriteMoviesEntity
import com.latihan.rmovies.model.local.entity.FavoriteTvShowsEntity
import com.latihan.rmovies.model.local.entity.TvShowsEntity
import com.latihan.rmovies.utils.SortUtils
import com.latihan.rmovies.vo.Resource
import junit.framework.TestCase
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteViewModelTest : TestCase() {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private var favViewModel: FavoriteViewModel? = null
    private val sort = SortUtils.DEFAULT

    @Mock
    private lateinit var dataRepository: DataRepository

    @Mock
    private lateinit var observerMovies: Observer<PagedList<FavoriteMoviesEntity>>

    @Mock
    private lateinit var observerShow: Observer<PagedList<FavoriteTvShowsEntity>>

    @Mock
    private lateinit var pagedList: PagedList<FavoriteTvShowsEntity>

    @Before
    public override fun setUp() {
        favViewModel = FavoriteViewModel(dataRepository)
    }

    @Test
    fun testGetFavShow() {

        val show = pagedList
        Mockito.`when`(show.size).thenReturn(20)

        val allShow = MutableLiveData<PagedList<FavoriteTvShowsEntity>>()
        allShow.value = show

        Mockito.lenient().`when`(dataRepository.getFavShows(sort)).thenReturn(allShow)
        val entity = favViewModel?.getFavShow(sort)?.value
        Mockito.verify(dataRepository).getFavShows(sort)

        Assert.assertNotNull(entity)
        Assert.assertEquals(20, entity?.size)

        favViewModel?.getFavShow(sort)?.observeForever(observerShow)
        Mockito.verify(observerShow).onChanged(show)
    }

}