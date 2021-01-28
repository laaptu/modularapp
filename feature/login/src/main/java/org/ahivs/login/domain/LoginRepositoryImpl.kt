package org.ahivs.login.domain

import org.ahivs.login.domain.data.LoggedInUser
import org.ahivs.login.domain.data.SignInUser
import javax.inject.Inject

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LoginRepositoryImpl @Inject constructor(private val loginApiService: LoginApiService) :
    LoginRepository {

    // in-memory cache of the loggedInUser object
    var user: LoggedInUser? = null
        private set

    init {
        user = null
    }

    override suspend fun login(email: String, password: String): LoggedInUser {
        // handle login
        val loggedInUser = loginApiService.login(SignInUser(email, password))
        saveLoggedInUser(loggedInUser)
        return loggedInUser
    }

    private fun saveLoggedInUser(loggedInUser: LoggedInUser) {
        this.user = loggedInUser
    }
}