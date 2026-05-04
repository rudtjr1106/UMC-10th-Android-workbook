package com.umc.workbook.data

import com.umc.workbook.data.model.ReqResUserListResponse
import com.umc.workbook.data.model.ReqResUserResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ReqResApiService {
    @GET("api/users/{id}")
    suspend fun getUser(
        @Path("id") id: Int
    ): ReqResUserResponse

    @GET("api/users")
    suspend fun getUsers(
        @Query("page") page: Int
    ): ReqResUserListResponse
}
