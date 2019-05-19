package com.squad.androidtemplate.ui.login.data.model.datasource

import com.squad.androidtemplate.ui.login.data.Result
import com.squad.androidtemplate.ui.login.data.model.LoggedInUser

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
interface LoginDataSource {

    fun login(username: String, password: String): Result<LoggedInUser>

    fun logout()
}

