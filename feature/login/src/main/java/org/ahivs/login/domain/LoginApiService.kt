package org.ahivs.login.domain

import org.ahivs.login.domain.data.LoggedInUser
import org.ahivs.login.domain.data.SignInUser
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApiService {
    @POST("/v1/accounts:signInWithPassword")
    suspend fun login(@Body signInUser: SignInUser): LoggedInUser
}

