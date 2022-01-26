package com.latihan.rmovies.ui.tvshows

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.latihan.rmovies.model.DataRepository
import com.latihan.rmovies.model.local.entity.FavoriteTvShowsEntity
import com.latihan.rmovies.model.local.entity.TvShowsEntity
import com.latihan.rmovies.ui.favorite.FavoriteViewModel
import com.latihan.rmovies.utils.DummyData
import com.latihan.rmovies.utils.SortUtils
import com.latihan.rmovies.vo.Resource
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


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private var viewModel: TvShowsViewModel? = null
    private var favViewModel: FavoriteViewModel? = null

    @Mock
    private lateinit var dataRepository: DataRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<TvShowsEntity>>>

    @Mock
    private lateinit var observerDetail: Observer<Resource<TvShowsEntity>>

    @Mock
    private lateinit var pagedList: PagedList<TvShowsEntity>

    @Mock
    private lateinit var favPagedList: PagedList<FavoriteTvShowsEntity>

    @Before
    public override fun setUp() {
        viewModel = TvShowsViewModel(dataRepository)
        favViewModel = FavoriteViewModel(dataRepository)
    }

    @Test
    fun testGetShows() {
        val show = Resource.success(pagedList)
        `when`(show.data?.size).thenReturn(20)

        val allShow = MutableLiveData<Resource<PagedList<TvShowsEntity>>>()
        allShow.value = show

        lenient().`when`(dataRepository.getTvShows()).thenReturn(allShow)
        val entity = viewModel?.getListShows()?.value?.data
        verify(dataRepository).getTvShows()

        Assert.assertNotNull(entity)
        Assert.assertEquals(20, entity?.size)

        viewModel?.getListShows()?.observeForever(observer)
        verify(observer).onChanged(show)
    }


    @Test
    fun testGetDetailShow() {
        val expect = MutableLiveData<Resource<TvShowsEntity>>()
        val dummyData = DummyData.getDummyRemoteTvShows()[0]
        val id = DummyData.getDummyRemoteTvShows()[0].id
        expect.value = Resource.success(dummyData)

        `when`(dataRepository.getTvShowDetail(id.toString())).thenReturn(expect)
        viewModel?.getDetailShow(id.toString())?.observeForever(observerDetail)

        verify(observerDetail).onChanged(expect.value)

        val actual = viewModel?.getDetailShow(id.toString())

        Assert.assertEquals(expect.value, actual?.value)

    }

    @Test
    fun testCheckFavShow() {

        val expect = 0
        val id = DummyData.getDummyRemoteTvShows()[0].id

        val actual = viewModel?.checkFavShow(id)

        assertEquals(expect, actual)
    }

    @Test
    fun testSetFavShow() {
        val sort = SortUtils.DEFAULT
        val dummyFav = TvShowsEntity(
            77169,
            "Cobra Kai",
            "2018-05-02",
            "This Karate Kid sequel series picks up 30 years after the events of the 1984 " +
                    "All Valley Karate Tournament and finds Johnny Lawrence on the hunt for redemption by reopening " +
                    "the infamous Cobra Kai karate dojo. This reignites his old rivalry with the successful Daniel LaRusso, " +
                    "who has been working to maintain the balance in his life without mentor Mr. Miyagi.",
            8.1,
            "/sWgBv7LV2PRoQgkxwlibdGXKz1S.jpg",
            "/6POBWybSBDBKjSs1VAQcnQC1qyt.jpg")

        viewModel?.setFavShow(dummyFav)
        val show = favPagedList
        `when`(show.size).thenReturn(1)

        val allShow = MutableLiveData<PagedList<FavoriteTvShowsEntity>>()
        allShow.value = show
        lenient().`when`(dataRepository.getFavShows(sort)).thenReturn(allShow)

        val actual = favViewModel?.getFavShow(sort)?.value

        assertEquals(1, actual?.size)
    }

    @Test
    fun testDelFavShow() {
        val sort = SortUtils.DEFAULT
        val dummyFav = TvShowsEntity(
            77169,
            "Cobra Kai",
            "2018-05-02",
            "This Karate Kid sequel series picks up 30 years after the events of the 1984 " +
                    "All Valley Karate Tournament and finds Johnny Lawrence on the hunt for redemption by reopening " +
                    "the infamous Cobra Kai karate dojo. This reignites his old rivalry with the successful Daniel LaRusso, " +
                    "who has been working to maintain the balance in his life without mentor Mr. Miyagi.",
            8.1,
            "/sWgBv7LV2PRoQgkxwlibdGXKz1S.jpg",
            "/6POBWybSBDBKjSs1VAQcnQC1qyt.jpg")

        viewModel?.deleteFavShow(dummyFav)
        val show = favPagedList
        `when`(show.size).thenReturn(0)

        val allShow = MutableLiveData<PagedList<FavoriteTvShowsEntity>>()
        allShow.value = show
        lenient().`when`(dataRepository.getFavShows(sort)).thenReturn(allShow)

        val actual = favViewModel?.getFavShow(sort)?.value

        assertEquals(0, actual?.size)

    }

}