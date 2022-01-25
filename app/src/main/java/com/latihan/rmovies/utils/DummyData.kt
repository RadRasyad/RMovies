package com.latihan.rmovies.utils

import com.latihan.rmovies.model.local.entity.FavoriteMoviesEntity
import com.latihan.rmovies.model.local.entity.FavoriteTvShowsEntity
import com.latihan.rmovies.model.local.entity.MoviesEntity
import com.latihan.rmovies.model.local.entity.TvShowsEntity

object DummyData {
    fun getDummyRemoteMovies(): List<MoviesEntity> =
        arrayListOf(
            MoviesEntity(
                634649,

                "Spider-Man: No Way Home",
                "Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero." +
                        " When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.",
                "2021-12-15",
                8.4,
                "/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
                "/3G1Q5xF40HkUBJXxt2DQgQzKTp5.jpg",),
            MoviesEntity(1,  "", "", "", 0.0, "", ""),
            MoviesEntity(2,  "", "", "", 0.0, "", ""),
            MoviesEntity(3,  "", "", "", 0.0, "",  ""),
            MoviesEntity(4,  "", "", "", 0.0, "", ""),
            MoviesEntity(5,  "", "", "", 0.0, "", ""),
            MoviesEntity(6,  "", "", "", 0.0, "", ""),
            MoviesEntity(7,  "", "", "", 0.0, "", ""),
            MoviesEntity(8,  "", "", "", 0.0, "", ""),
            MoviesEntity(9,  "", "", "", 0.0, "", ""),
            MoviesEntity(10,  "", "", "", 0.0, "", ""),
            MoviesEntity(11,  "", "", "", 0.0, "", ""),
            MoviesEntity(12,  "", "", "", 0.0, "", ""),
            MoviesEntity(13,  "", "", "", 0.0, "", ""),
            MoviesEntity(14,  "", "", "", 0.0, "", ""),
            MoviesEntity(15,  "", "", "", 0.0, "", ""),
            MoviesEntity(16,  "", "", "", 0.0, "", ""),
            MoviesEntity(17,  "", "", "", 0.0, "", ""),
            MoviesEntity(18,  "", "", "", 0.0, "", ""),
            MoviesEntity(19,  "", "", "", 0.0, "", "")
        )

    fun getMovieDetail() =
        MoviesEntity(
            634649,

            "Spider-Man: No Way Home",
            "Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero." +
                    " When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.",
            "2021-12-15",
            8.4,
            "/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
            "/3G1Q5xF40HkUBJXxt2DQgQzKTp5.jpg"
        )

    fun getDummyRemoteTvShows(): List<TvShowsEntity> =
        arrayListOf(
            TvShowsEntity(
                77169,
                "Cobra Kai",
                "2018-05-02",
                "This Karate Kid sequel series picks up 30 years after the events of the 1984 " +
                        "All Valley Karate Tournament and finds Johnny Lawrence on the hunt for redemption by reopening " +
                        "the infamous Cobra Kai karate dojo. This reignites his old rivalry with the successful Daniel LaRusso, " +
                        "who has been working to maintain the balance in his life without mentor Mr. Miyagi.",
                8.1,
                "/sWgBv7LV2PRoQgkxwlibdGXKz1S.jpg",
                "/6POBWybSBDBKjSs1VAQcnQC1qyt.jpg",
            ),
            TvShowsEntity(1, "", "", "", 0.0, "", ""),
            TvShowsEntity(2, "", "", "", 0.0, "", ""),
            TvShowsEntity(3, "", "", "", 0.0, "", ""),
            TvShowsEntity(4, "", "", "", 0.0, "", ""),
            TvShowsEntity(5, "", "", "", 0.0, "", ""),
            TvShowsEntity(6, "", "", "", 0.0, "", ""),
            TvShowsEntity(7, "", "", "", 0.0, "", ""),
            TvShowsEntity(8, "", "", "", 0.0, "", ""),
            TvShowsEntity(9, "", "", "", 0.0, "", ""),
            TvShowsEntity(10, "", "", "", 0.0, "", ""),
            TvShowsEntity(11, "", "", "", 0.0, "", ""),
            TvShowsEntity(12, "", "", "", 0.0, "", ""),
            TvShowsEntity(13, "", "", "", 0.0, "", ""),
            TvShowsEntity(14, "", "", "", 0.0, "", ""),
            TvShowsEntity(15, "", "", "", 0.0, "", ""),
            TvShowsEntity(16, "", "", "", 0.0, "", ""),
            TvShowsEntity(17, "", "", "", 0.0, "", ""),
            TvShowsEntity(18, "", "", "", 0.0, "", ""),
            TvShowsEntity(19, "", "", "", 0.0, "", ""),

        )

    fun getTvShowDetail(): TvShowsEntity =
        TvShowsEntity(
            77169,

            "Cobra Kai",
            "2018-05-02",
            "This Karate Kid sequel series picks up 30 years after the events of the 1984 " +
                    "All Valley Karate Tournament and finds Johnny Lawrence on the hunt for redemption by reopening " +
                    "the infamous Cobra Kai karate dojo. This reignites his old rivalry with the successful Daniel LaRusso, " +
                    "who has been working to maintain the balance in his life without mentor Mr. Miyagi.",
            8.1,
            "/sWgBv7LV2PRoQgkxwlibdGXKz1S.jpg",
            "/6POBWybSBDBKjSs1VAQcnQC1qyt.jpg"



        )

    fun getDummyFavoriteMovies(): List<FavoriteMoviesEntity> =
        arrayListOf(
            FavoriteMoviesEntity(
                634649,

                "Spider-Man: No Way Home",
                "2021-12-15",
                "Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero." +
                        " When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.",

                8.4,
                "/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
                "/3G1Q5xF40HkUBJXxt2DQgQzKTp5.jpg",),
            FavoriteMoviesEntity(1,  "", "", "", 0.0, "", ""),
            FavoriteMoviesEntity(2,  "", "", "", 0.0, "", ""),
            FavoriteMoviesEntity(3,  "", "", "", 0.0, "",  ""),
            FavoriteMoviesEntity(4,  "", "", "", 0.0, "", ""),
            FavoriteMoviesEntity(5,  "", "", "", 0.0, "", ""),
            FavoriteMoviesEntity(6,  "", "", "", 0.0, "", ""),
            FavoriteMoviesEntity(7,  "", "", "", 0.0, "", ""),
            FavoriteMoviesEntity(8,  "", "", "", 0.0, "", ""),
            FavoriteMoviesEntity(9,  "", "", "", 0.0, "", ""),
            FavoriteMoviesEntity(10,  "", "", "", 0.0, "", ""),
            FavoriteMoviesEntity(11,  "", "", "", 0.0, "", ""),
            FavoriteMoviesEntity(12,  "", "", "", 0.0, "", ""),
            FavoriteMoviesEntity(13,  "", "", "", 0.0, "", ""),
            FavoriteMoviesEntity(14,  "", "", "", 0.0, "", ""),
            FavoriteMoviesEntity(15,  "", "", "", 0.0, "", ""),
            FavoriteMoviesEntity(16,  "", "", "", 0.0, "", ""),
            FavoriteMoviesEntity(17,  "", "", "", 0.0, "", ""),
            FavoriteMoviesEntity(18,  "", "", "", 0.0, "", ""),
            FavoriteMoviesEntity(19,  "", "", "", 0.0, "", "")
        )


    fun getDummyFavoriteShow(): List<FavoriteTvShowsEntity> =
        arrayListOf(
            FavoriteTvShowsEntity(
                77169,
                "Cobra Kai",
                "2018-05-02",
                "This Karate Kid sequel series picks up 30 years after the events of the 1984 " +
                        "All Valley Karate Tournament and finds Johnny Lawrence on the hunt for redemption by reopening " +
                        "the infamous Cobra Kai karate dojo. This reignites his old rivalry with the successful Daniel LaRusso, " +
                        "who has been working to maintain the balance in his life without mentor Mr. Miyagi.",
                8.1,
                "/sWgBv7LV2PRoQgkxwlibdGXKz1S.jpg",
                "/6POBWybSBDBKjSs1VAQcnQC1qyt.jpg"),
            FavoriteTvShowsEntity(1,  "", "", "", 0.0, "", ""),
            FavoriteTvShowsEntity(2,  "", "", "", 0.0, "", ""),
            FavoriteTvShowsEntity(3,  "", "", "", 0.0, "",  ""),
            FavoriteTvShowsEntity(4,  "", "", "", 0.0, "", ""),
            FavoriteTvShowsEntity(5,  "", "", "", 0.0, "", ""),
            FavoriteTvShowsEntity(6,  "", "", "", 0.0, "", ""),
            FavoriteTvShowsEntity(7,  "", "", "", 0.0, "", ""),
            FavoriteTvShowsEntity(8,  "", "", "", 0.0, "", ""),
            FavoriteTvShowsEntity(9,  "", "", "", 0.0, "", ""),
            FavoriteTvShowsEntity(10,  "", "", "", 0.0, "", ""),
            FavoriteTvShowsEntity(11,  "", "", "", 0.0, "", ""),
            FavoriteTvShowsEntity(12,  "", "", "", 0.0, "", ""),
            FavoriteTvShowsEntity(13,  "", "", "", 0.0, "", ""),
            FavoriteTvShowsEntity(14,  "", "", "", 0.0, "", ""),
            FavoriteTvShowsEntity(15,  "", "", "", 0.0, "", ""),
            FavoriteTvShowsEntity(16,  "", "", "", 0.0, "", ""),
            FavoriteTvShowsEntity(17,  "", "", "", 0.0, "", ""),
            FavoriteTvShowsEntity(18,  "", "", "", 0.0, "", ""),
            FavoriteTvShowsEntity(19,  "", "", "", 0.0, "", "")
        )

}