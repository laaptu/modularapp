package org.ahivs.login.domain

import org.ahivs.login.domain.data.LoggedInUser

interface LoginRepository {
    suspend fun login(email: String, password: String): LoggedInUser
}
