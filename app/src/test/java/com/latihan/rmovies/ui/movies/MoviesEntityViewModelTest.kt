package com.latihan.rmovies.ui.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.latihan.rmovies.model.DataRepository
import com.latihan.rmovies.model.local.entity.FavoriteMoviesEntity
import com.latihan.rmovies.model.local.entity.MoviesEntity
import com.latihan.rmovies.ui.favorite.FavoriteViewModel
import com.latihan.rmovies.utils.DummyData
import com.latihan.rmovies.utils.SortUtils
import com.latihan.rmovies.vo.Resource
import junit.framework.TestCase
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
    private var favViewModel: FavoriteViewModel? = null

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

    @Mock
    private lateinit var favMoviePagedList: PagedList<FavoriteMoviesEntity>

    @Before
    fun setUp() {
        viewModel = MoviesViewModel(dataRepository)
        favViewModel = FavoriteViewModel(dataRepository)
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

    @Test
    fun testCheckFavMovie() {

        val expect = 0
        val id = DummyData.getDummyRemoteMovies()[0].id

        val actual = viewModel?.checkFavMovies(id)

        TestCase.assertEquals(expect, actual)
    }

    @Test
    fun testSetFavShow() {
        val sort = SortUtils.DEFAULT
        val dummyFav = MoviesEntity(
            634649,
            "Spider-Man: No Way Home",
            "2021-12-15",
            "Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero." +
                    " When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.",
            8.4,
            "/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
            "/3G1Q5xF40HkUBJXxt2DQgQzKTp5.jpg",)

        viewModel?.setFavMovies(dummyFav)
        val movie = favMoviePagedList
        `when`(movie.size).thenReturn(1)

        val allMovie = MutableLiveData<PagedList<FavoriteMoviesEntity>>()
        allMovie.value = movie
        lenient().`when`(dataRepository.getFavMovies(sort)).thenReturn(allMovie)

        val actual = favViewModel?.getFavMovies(sort)?.value

        TestCase.assertEquals(1, actual?.size)
    }

    @Test
    fun testDelFavShow() {
        val sort = SortUtils.DEFAULT
        val dummyFav = MoviesEntity(
            634649,
            "Spider-Man: No Way Home",
            "2021-12-15",
            "Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero." +
                    " When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.",
            8.4,
            "/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
            "/3G1Q5xF40HkUBJXxt2DQgQzKTp5.jpg",)

        viewModel?.deleteFavMovies(dummyFav)
        val movie = favMoviePagedList
        `when`(movie.size).thenReturn(0)

        val allMovie = MutableLiveData<PagedList<FavoriteMoviesEntity>>()
        allMovie.value = movie
        lenient().`when`(dataRepository.getFavMovies(sort)).thenReturn(allMovie)

        val actual = favViewModel?.getFavMovies(sort)?.value

        TestCase.assertEquals(0, actual?.size)

    }


}