package com.squad.androidtemplate.ui.login.data

import com.squad.androidtemplate.ui.login.data.model.LoggedInUser

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LoginRepository(
    val localDataSource: LoginDataSource,
    val remoteDataSource: LoginDataSource
) {

    // in-memory cache of the loggedInUser object
    var user: LoggedInUser? = null
        private set

    val isLoggedIn: Boolean
        get() = user != null

    init {
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
        user = null
    }

    fun logout() {
        user = null
        localDataSource.logout()
        remoteDataSource.logout()
    }

    fun login(username: String, password: String): Result<LoggedInUser> {
        // handle login
        val result = remoteDataSource.login(username, password)

        if (result is Result.Success) {
            setLoggedInUser(result.data)
        }

        return result
    }

    private fun setLoggedInUser(loggedInUser: LoggedInUser) {
        this.user = loggedInUser
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }

    companion object {

        private var INSTANCE: LoginRepository? = null

        /**
         * Returns the single instance of this class, creating it if necessary.

         * @param loginRemoteDataSource the backend data source
         * *
         * @param loginLocalDataSource  the device storage data source
         * *
         * @return the [LoginRepository] instance
         */
        @JvmStatic
        fun getInstance(
            tasksRemoteDataSource: LoginDataSource,
            tasksLocalDataSource: LoginDataSource
        ) =
            INSTANCE ?: synchronized(LoginRepository::class.java) {
                INSTANCE ?: LoginRepository(tasksLocalDataSource, tasksRemoteDataSource)
                    .also { INSTANCE = it }
            }


        /**
         * Used to force [getInstance] to create a new instance
         * next time it's called.
         */
        @JvmStatic
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}
