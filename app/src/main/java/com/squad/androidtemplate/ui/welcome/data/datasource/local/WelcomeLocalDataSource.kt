package com.squad.androidtemplate.ui.welcome.data.datasource.local

import androidx.annotation.VisibleForTesting
import com.squad.androidtemplate.ui.login.data.model.datasource.WelcomeDataSource
import com.squad.androidtemplate.utils.AppExecutors

class WelcomeLocalDataSource private constructor(
    val appExecutors: AppExecutors
) : WelcomeDataSource {

    init {

    }

    companion object {
        private var INSTANCE: WelcomeLocalDataSource? = null

        @JvmStatic
        fun getInstance(appExecutors: AppExecutors): WelcomeLocalDataSource {
            if (INSTANCE == null) {
                synchronized(WelcomeLocalDataSource::javaClass) {
                    INSTANCE = WelcomeLocalDataSource(appExecutors)
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