package org.ahivs.login.domain

import org.ahivs.login.domain.LoginRepository
import org.ahivs.login.domain.data.LoggedInUser
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val loginRepository: LoginRepository) {
    suspend fun invoke(username: String, password: String): LoggedInUser = loginRepository.login(username, password)
}