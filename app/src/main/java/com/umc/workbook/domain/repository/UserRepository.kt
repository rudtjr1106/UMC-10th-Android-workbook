package com.umc.workbook.domain.repository

import com.umc.workbook.data.model.ReqResUser

interface UserRepository {
    suspend fun getUser(id: Int): ReqResUser
    suspend fun getUsers(page: Int): List<ReqResUser>
}
