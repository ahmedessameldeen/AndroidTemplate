package com.squad.androidtemplate.di

import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.squad.androidtemplate.Injection
import com.squad.androidtemplate.R
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



    //layout Managers
    factory { androidx.recyclerview.widget.LinearLayoutManager(androidContext()) }

    //viewModels
    viewModel { LoginViewModel(get()) }
    viewModel { RegisterViewModel(get()) }
    viewModel {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(androidContext().resources.getString(R.string.google_web_client_id))
            .requestEmail()
            .build()
        WelcomeViewModel(get(), GoogleSignIn.getClient(androidContext(), gso))
    }
    //repositories
    factory { Injection.provideLoginRepository(androidContext()) }
    factory { RegisterRepository(get()) }
    factory { WelcomeRepository(get()) }
    factory { RegisterDataSource() }
    factory { WelcomeDataSource() }

}

