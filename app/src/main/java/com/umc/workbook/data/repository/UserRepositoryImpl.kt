package com.umc.workbook.data.repository

import com.umc.workbook.data.ReqResApiService
import com.umc.workbook.domain.repository.UserRepository
import com.umc.workbook.data.model.ReqResUser
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val apiService: ReqResApiService
) : UserRepository {

    override suspend fun getUser(id: Int): ReqResUser {
        return apiService.getUser(id).data
    }

    override suspend fun getUsers(page: Int): List<ReqResUser> {
        return apiService.getUsers(page).data
    }
}
