package com.umc.workbook.data.model

import com.google.gson.annotations.SerializedName

data class ReqResUserResponse(
    val data: ReqResUser,
    val support: ReqResSupport
)

data class ReqResUserListResponse(
    val page: Int,
    val data: List<ReqResUser>,
    val support: ReqResSupport
)

data class ReqResUser(
    val id: Int,
    val email: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    val avatar: String
)

data class ReqResSupport(
    val url: String,
    val text: String
)
