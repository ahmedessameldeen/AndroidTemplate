package com.squad.androidtemplate.di

import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.squad.androidtemplate.ui.login.data.LoginDataSource
import com.squad.androidtemplate.ui.login.data.LoginRepository
import com.squad.androidtemplate.ui.login.data.WelcomeDataSource
import com.squad.androidtemplate.ui.login.data.WelcomeRepository
import com.squad.androidtemplate.ui.login.ui.login.LoginViewModel
import com.squad.androidtemplate.ui.register.data.RegisterDataSource
import com.squad.androidtemplate.ui.register.data.RegisterRepository
import com.squad.androidtemplate.ui.register.ui.register.RegisterViewModel
import com.squad.androidtemplate.ui.welcome.ui.welcome.WelcomeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module


val appModule = module {

    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestEmail()
        .build()

    //layout Managers
    factory { androidx.recyclerview.widget.LinearLayoutManager(androidContext()) }

    //viewModels
    viewModel { LoginViewModel(get()) }
    viewModel { RegisterViewModel(get()) }
    viewModel { WelcomeViewModel(get(), GoogleSignIn.getClient(androidContext(), gso)) }

    //repositories
    factory { LoginRepository(get()) }
    factory { RegisterRepository(get()) }
    factory { WelcomeRepository(get()) }
    factory { LoginDataSource() }
    factory { RegisterDataSource() }
    factory { WelcomeDataSource() }

}

