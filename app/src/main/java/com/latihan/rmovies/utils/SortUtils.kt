package com.latihan.rmovies.utils

import androidx.sqlite.db.SimpleSQLiteQuery

object SortUtils {

    const val NEWEST = "Newest"
    const val OLDEST = "Oldest"

    fun getSortedQuery(filter: String): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM movie WHERE favorite = 1 ")
        if (filter == NEWEST) {
            simpleQuery.append("ORDER BY title DESC")
        } else if (filter == OLDEST) {
            simpleQuery.append("ORDER BY title ASC")
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }

}