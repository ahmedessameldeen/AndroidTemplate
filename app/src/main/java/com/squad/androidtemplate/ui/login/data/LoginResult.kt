package com.squad.androidtemplate.ui.login.data

import com.squad.androidtemplate.ui.login.ui.LoggedInUserView

/**
 * Authentication result : success (user details) or error message.
 */
data class LoginResult(
    val success: LoggedInUserView? = null,
    val error: Int? = null
)
