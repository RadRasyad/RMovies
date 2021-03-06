package com.latihan.rmovies.model.remote

import com.latihan.rmovies.model.remote.StatusResponse.SUCCESS
import com.latihan.rmovies.model.remote.StatusResponse.ERROR

class ApiResponse<T>(val status: StatusResponse, var body: T, val message: String?) {
    companion object {
        fun <T> success(body: T): ApiResponse<T> = ApiResponse(SUCCESS, body, null)

        fun <T> error(msg: String, body: T): ApiResponse<T> = ApiResponse(ERROR, body, msg)
    }

}