package com.squad.androidtemplate.ui.welcome.data

import com.squad.androidtemplate.ui.login.data.model.datasource.WelcomeDataSource

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of welcome status and user credentials information.
 */

class WelcomeRepository(val localDataSource: WelcomeDataSource, val remoteDataSource: WelcomeDataSource) {


    companion object {

        private var INSTANCE: WelcomeRepository? = null

        /**
         * Returns the single instance of this class, creating it if necessary.

         * @param welcomeRemoteDataSource the backend data source
         * *
         * @param welcomeLocalDataSource  the device storage data source
         * *
         * @return the [WelcomeRepository] instance
         */
        @JvmStatic
        fun getInstance(
            welcomeRemoteDataSource: WelcomeDataSource,
            welcomeLocalDataSource: WelcomeDataSource
        ) =
            INSTANCE ?: synchronized(WelcomeRepository::class.java) {
                INSTANCE ?: WelcomeRepository(welcomeLocalDataSource, welcomeRemoteDataSource)
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
