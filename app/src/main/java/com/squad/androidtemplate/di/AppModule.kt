package com.squad.androidtemplate.di

import com.squad.androidtemplate.ui.login.data.LoginDataSource
import com.squad.androidtemplate.ui.login.data.LoginRepository
import com.squad.androidtemplate.ui.login.ui.login.LoginViewModel
import com.squad.androidtemplate.ui.register.data.RegisterDataSource
import com.squad.androidtemplate.ui.register.data.RegisterRepository
import com.squad.androidtemplate.ui.register.ui.register.RegisterViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val appModule = module {

    //layout Managers
    factory { androidx.recyclerview.widget.LinearLayoutManager(null) }

    //viewModels
    viewModel { LoginViewModel(get()) }
    viewModel { RegisterViewModel(get()) }

    //repositories
    factory { LoginRepository(get()) }
    factory { RegisterRepository(get()) }
    factory { LoginDataSource() }
    factory { RegisterDataSource() }
}