package org.ahivs.login.domain.data

data class SignInUser(
        val email: String,
        val password: String,
        val returnSecureToken: Boolean = true
)

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
data class LoggedInUser(
        val email: String,
        val displayName: String,
        val idToken: String,
        val refreshToken: String
)