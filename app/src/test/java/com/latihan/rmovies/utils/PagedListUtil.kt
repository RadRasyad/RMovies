package com.latihan.rmovies.utils

import androidx.paging.PagedList
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

object PagedListUtil {

    fun <T : Any> mockPagedList(list: List<T>?): PagedList<T> {
        val pagedList = mock(PagedList::class.java) as PagedList<T>
        `when`(pagedList[anyInt()]).then { invocation ->
            val index = invocation.arguments.first() as Int
            list?.get(index)
        }
        `when`(pagedList.size).thenReturn(list?.size)

        return pagedList
    }
}