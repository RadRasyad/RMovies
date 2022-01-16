package com.latihan.rmovies.utils

import com.latihan.rmovies.model.local.entity.Item
import com.latihan.rmovies.model.local.entity.TvShowDetails

object DummyData {
    fun getDummyRemoteMovies(): List<Item> =
        arrayListOf(
            Item(
                634649,

                "Spider-Man: No Way Home",
                "",
                "Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero." +
                        " When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.",
                "2021-12-15",
                8.4,
                "/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
                "/3G1Q5xF40HkUBJXxt2DQgQzKTp5.jpg",),
            Item(1, "", "", "", "", 0.0, "", ""),
            Item(2, "", "", "", "", 0.0, "", ""),
            Item(3, "", "", "", "", 0.0, "",  ""),
            Item(4, "", "", "", "", 0.0, "", ""),
            Item(5, "", "", "", "", 0.0, "", ""),
            Item(6, "", "", "", "", 0.0, "", ""),
            Item(7, "", "", "", "", 0.0, "", ""),
            Item(8, "", "", "", "", 0.0, "", ""),
            Item(9, "", "", "", "", 0.0, "", ""),
            Item(10, "", "", "", "", 0.0, "", ""),
            Item(11, "", "", "", "", 0.0, "", ""),
            Item(12, "", "", "", "", 0.0, "", ""),
            Item(13, "", "", "", "", 0.0, "", ""),
            Item(14, "", "", "", "", 0.0, "", ""),
            Item(15, "", "", "", "", 0.0, "", ""),
            Item(16, "", "", "", "", 0.0, "", ""),
            Item(17, "", "", "", "", 0.0, "", ""),
            Item(18, "", "", "", "", 0.0, "", ""),
            Item(19, "", "", "", "", 0.0, "", "")
        )

    fun getMovieDetail() =
        Item(
            634649,

            "Spider-Man: No Way Home",
            "",
            "Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero." +
                    " When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.",
            "2021-12-15",
            8.4,
            "/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
            "/3G1Q5xF40HkUBJXxt2DQgQzKTp5.jpg"
        )

    fun getDummyRemoteTvShows(): List<Item> =
        arrayListOf(
            Item(
                77169,

                "",
                "Cobra Kai",
                "This Karate Kid sequel series picks up 30 years after the events of the 1984 " +
                        "All Valley Karate Tournament and finds Johnny Lawrence on the hunt for redemption by reopening " +
                        "the infamous Cobra Kai karate dojo. This reignites his old rivalry with the successful Daniel LaRusso, " +
                        "who has been working to maintain the balance in his life without mentor Mr. Miyagi.",
                "",
                8.1,
                "/sWgBv7LV2PRoQgkxwlibdGXKz1S.jpg",
                "/6POBWybSBDBKjSs1VAQcnQC1qyt.jpg",
            ),
            Item(1, "", "", "", "", 0.0, "", ""),
            Item(2, "", "", "", "", 0.0, "", ""),
            Item(3, "", "", "", "", 0.0, "",  ""),
            Item(4, "", "", "", "", 0.0, "", ""),
            Item(5, "", "", "", "", 0.0, "", ""),
            Item(6, "", "", "", "", 0.0, "", ""),
            Item(7, "", "", "", "", 0.0, "", ""),
            Item(8, "", "", "", "", 0.0, "", ""),
            Item(9, "", "", "", "", 0.0, "", ""),
            Item(10, "", "", "", "", 0.0, "", ""),
            Item(11, "", "", "", "", 0.0, "", ""),
            Item(12, "", "", "", "", 0.0, "", ""),
            Item(13, "", "", "", "", 0.0, "", ""),
            Item(14, "", "", "", "", 0.0, "", ""),
            Item(15, "", "", "", "", 0.0, "", ""),
            Item(16, "", "", "", "", 0.0, "", ""),
            Item(17, "", "", "", "", 0.0, "", ""),
            Item(18, "", "", "", "", 0.0, "", ""),
            Item(19, "", "", "", "", 0.0, "", "")
        )

    fun getTvShowDetail(): TvShowDetails =
        TvShowDetails(
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
}