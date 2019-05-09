package com.squad.androidtemplate.ui.login.data.model.data.source.local

import androidx.annotation.VisibleForTesting
import com.squad.androidtemplate.ui.login.data.LoginDataSource
import com.squad.androidtemplate.ui.login.data.Result
import com.squad.androidtemplate.ui.login.data.model.LoggedInUser
import com.squad.androidtemplate.utils.AppExecutors
import java.io.IOException

class LoginLocalDataSource private constructor(
    val appExecutors: AppExecutors
) : LoginDataSource {

    override fun login(username: String, password: String): Result<LoggedInUser> {
        try {
            // TODO: handle loggedInUser authentication
            val fakeUser = LoggedInUser(java.util.UUID.randomUUID().toString(), "Local Data User")
            return Result.Success(fakeUser)
        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }

    override fun logout() {
    }

    init {

    }

    companion object {
        private var INSTANCE: LoginLocalDataSource? = null

        @JvmStatic
        fun getInstance(appExecutors: AppExecutors): LoginLocalDataSource {
            if (INSTANCE == null) {
                synchronized(LoginLocalDataSource::javaClass) {
                    INSTANCE = LoginLocalDataSource(appExecutors)
                }
            }
            return INSTANCE!!
        }

        @VisibleForTesting
        fun clearInstance() {
            INSTANCE = null
        }
    }
}