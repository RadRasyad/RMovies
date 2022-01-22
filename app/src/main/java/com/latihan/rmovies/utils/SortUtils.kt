package com.latihan.rmovies.utils

import androidx.sqlite.db.SimpleSQLiteQuery

object SortUtils {

    const val DEFAULT = "Default"
    const val NAME = "Name"
    const val VOTE = "VoteAverage"

    fun getSortedMovies(filter: String): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM movie WHERE favorite = 1 ")
        when (filter) {
            NAME -> {
                simpleQuery.append("ORDER BY title ASC")
            }
            VOTE -> {
                simpleQuery.append("ORDER BY voteAverage DESC")
            }
            DEFAULT -> {
                simpleQuery.append("ORDER BY id DESC")
            }
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }

    fun getSortedShows(filter: String): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM tvshow WHERE favorite = 1 ")
        when (filter) {
            NAME -> {
                simpleQuery.append("ORDER BY name ASC")
            }
            VOTE -> {
                simpleQuery.append("ORDER BY voteAverage DESC")
            }
            DEFAULT -> {
                simpleQuery.append("ORDER BY id DESC")
            }
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }

}