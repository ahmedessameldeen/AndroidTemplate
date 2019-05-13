/*
 * Copyright 2017, The Android Open Source Project
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
package com.example.android.architecture.blueprints.todoapp.data

import com.squad.androidtemplate.model.data.Task
import com.squad.androidtemplate.ui.login.data.Result
import com.squad.androidtemplate.ui.login.data.model.LoggedInUser
import com.squad.androidtemplate.ui.login.data.model.datasource.LoginDataSource
import java.io.IOException
import java.util.*

/**
 * Implementation of a remote data source with static access to the data for easy testing.
 */
object FakeLoginRemoteDataSource : LoginDataSource {
    override fun login(username: String, password: String): Result<LoggedInUser> {
        try {
            val fakeUser = LoggedInUser(java.util.UUID.randomUUID().toString(), "Fake User")
            return Result.Success(fakeUser)
        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }

    override fun logout() {

    }

    private var TASKS_SERVICE_DATA: LinkedHashMap<String, Task> = LinkedHashMap()

}
