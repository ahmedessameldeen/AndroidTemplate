package com.squad.androidtemplate.ui.login.data.model.data.source.remote

import com.squad.androidtemplate.ui.login.data.LoginDataSource
import com.squad.androidtemplate.ui.login.data.Result
import com.squad.androidtemplate.ui.login.data.model.LoggedInUser
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