/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.squad.androidtemplate

import android.content.Context
import com.example.android.architecture.blueprints.todoapp.data.FakeWelcomeRemoteDataSource
import com.squad.androidtemplate.data.FakeLoginRemoteDataSource
import com.squad.androidtemplate.ui.login.data.LoginRepository
import com.squad.androidtemplate.ui.login.data.model.datasource.local.LoginLocalDataSource
import com.squad.androidtemplate.ui.welcome.data.WelcomeRepository
import com.squad.androidtemplate.ui.welcome.data.datasource.local.WelcomeLocalDataSource
import com.squad.androidtemplate.utils.AppExecutors


/**
 * Enables injection of mock implementations for
 * [TasksDataSource] at compile time. This is useful for testing, since it allows us to use
 * a fake instance of the class to isolate the dependencies and run a test hermetically.
 */
object Injection {

    fun provideLoginRepository(context: Context): LoginRepository {
        return LoginRepository.getInstance(
            FakeLoginRemoteDataSource,
            LoginLocalDataSource.getInstance(AppExecutors())
        )
    }

    fun provideWelcomeRepository(context: Context): WelcomeRepository {
        return WelcomeRepository.getInstance(
            FakeWelcomeRemoteDataSource,
            WelcomeLocalDataSource.getInstance(AppExecutors())
        )
    }
}
