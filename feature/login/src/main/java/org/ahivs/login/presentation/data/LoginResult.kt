package org.ahivs.login.presentation.data

import org.ahivs.login.presentation.data.LoggedInUserView

/**
 * Authentication result : success (user details) or error message.
 */
data class LoginResult(
    val success: LoggedInUserView? = null,
    val error: Int? = null,
    var errorMsg: String? = null
)
