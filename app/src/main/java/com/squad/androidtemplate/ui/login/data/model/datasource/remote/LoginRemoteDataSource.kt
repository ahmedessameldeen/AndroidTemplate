package com.squad.androidtemplate.ui.login.data.model.datasource.remote

import com.squad.androidtemplate.ui.login.data.Result
import com.squad.androidtemplate.ui.login.data.model.LoggedInUser
import com.squad.androidtemplate.ui.login.data.model.datasource.LoginDataSource
import java.io.IOException

object LoginRemoteDataSource : LoginDataSource {

    override fun login(username: String, password: String): Result<LoggedInUser> {
        try {
            // TODO: handle loggedInUser authentication
            val fakeUser = LoggedInUser(java.util.UUID.randomUUID().toString(), "Remote Data User")
            return Result.Success(fakeUser)
        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }

    override fun logout() {
    }

    init {

    }
}