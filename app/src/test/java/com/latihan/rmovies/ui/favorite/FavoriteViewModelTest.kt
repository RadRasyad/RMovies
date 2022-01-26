package com.latihan.rmovies.ui.favorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.latihan.rmovies.model.DataRepository
import com.latihan.rmovies.model.local.entity.FavoriteMoviesEntity
import com.latihan.rmovies.model.local.entity.FavoriteTvShowsEntity
import com.latihan.rmovies.utils.DummyData
import com.latihan.rmovies.utils.SortUtils
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
    private lateinit var observerMDetail: Observer<FavoriteMoviesEntity>

    @Mock
    private lateinit var observerShow: Observer<PagedList<FavoriteTvShowsEntity>>

    @Mock
    private lateinit var observerSDetail: Observer<FavoriteTvShowsEntity>

    @Mock
    private lateinit var favMoviePagedList: PagedList<FavoriteMoviesEntity>

    @Mock
    private lateinit var favShowPagedList: PagedList<FavoriteTvShowsEntity>

    @Before
    public override fun setUp() {
        favViewModel = FavoriteViewModel(dataRepository)
    }

    @Test
    fun testGetFavMovies() {

        val movie = favMoviePagedList
        Mockito.`when`(movie.size).thenReturn(1)

        val allMovie = MutableLiveData<PagedList<FavoriteMoviesEntity>>()
        allMovie.value = movie

        Mockito.lenient().`when`(dataRepository.getFavMovies(sort)).thenReturn(allMovie)
        val entity = favViewModel?.getFavMovies(sort)?.value
        Mockito.verify(dataRepository).getFavMovies(sort)

        Assert.assertNotNull(entity)
        Assert.assertEquals(1, entity?.size)

        favViewModel?.getFavMovies(sort)?.observeForever(observerMovies)
        Mockito.verify(observerMovies).onChanged(movie)
    }

    @Test
    fun testGetFavMDetail() {
        val expect = MutableLiveData<FavoriteMoviesEntity>()
        val dummyData = DummyData.getDummyFavoriteMovies()[0]
        val id = DummyData.getDummyFavoriteMovies()[0].id
        expect.value = dummyData

        Mockito.`when`(dataRepository.getFavMDetail(id.toString())).thenReturn(expect)
        favViewModel?.getFavMDetail(id.toString())?.observeForever(observerMDetail)

        Mockito.verify(observerMDetail).onChanged(expect.value)

        val actual = favViewModel?.getFavMDetail(id.toString())

        Assert.assertEquals(expect.value, actual?.value)
    }

    @Test
    fun testDelFavMovie() {
        val sort = SortUtils.DEFAULT
        val dummyFav = FavoriteMoviesEntity(
            634649,

            "Spider-Man: No Way Home",
            "2021-12-15",
            "Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero." +
                    " When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.",

            8.4,
            "/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
            "/3G1Q5xF40HkUBJXxt2DQgQzKTp5.jpg",)

        favViewModel?.delFavMovie(dummyFav)
        val show = favMoviePagedList
        Mockito.`when`(show.size).thenReturn(0)

        val allMovie = MutableLiveData<PagedList<FavoriteMoviesEntity>>()
        allMovie.value = show
        Mockito.lenient().`when`(dataRepository.getFavMovies(sort)).thenReturn(allMovie)

        val actual = favViewModel?.getFavMovies(sort)?.value

        assertEquals(0, actual?.size)

    }

    @Test
    fun testGetFavShow() {

        val show = favShowPagedList
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

    @Test
    fun testGetFavSDetail() {
        val expect = MutableLiveData<FavoriteTvShowsEntity>()
        val dummyData = DummyData.getDummyFavoriteShow()[0]
        val id = DummyData.getDummyFavoriteShow()[0].id
        expect.value = dummyData

        Mockito.`when`(dataRepository.getFavSDetail(id.toString())).thenReturn(expect)
        favViewModel?.getFavSDetail(id.toString())?.observeForever(observerSDetail)

        Mockito.verify(observerSDetail).onChanged(expect.value)

        val actual = favViewModel?.getFavSDetail(id.toString())

        Assert.assertEquals(expect.value, actual?.value)
    }

    @Test
    fun testDelFavShow() {
        val sort = SortUtils.DEFAULT
        val dummyFav = FavoriteTvShowsEntity(
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

        favViewModel?.delFavShow(dummyFav)
        val show = favShowPagedList
        Mockito.`when`(show.size).thenReturn(0)

        val allShow = MutableLiveData<PagedList<FavoriteTvShowsEntity>>()
        allShow.value = show
        Mockito.lenient().`when`(dataRepository.getFavShows(sort)).thenReturn(allShow)

        val actual = favViewModel?.getFavShow(sort)?.value

        assertEquals(0, actual?.size)

    }

}