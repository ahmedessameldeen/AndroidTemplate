package com.squad.androidtemplate.di

import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.squad.androidtemplate.R
import com.squad.androidtemplate.ui.register.data.RegisterDataSource
import com.squad.androidtemplate.ui.register.data.RegisterRepository
import com.squad.androidtemplate.ui.register.ui.register.RegisterViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module


val appModule = module {



    //layout Managers
    factory { androidx.recyclerview.widget.LinearLayoutManager(androidContext()) }

    //viewModels
    viewModel { RegisterViewModel(get()) }


    //repositories
    factory { RegisterRepository(get()) }
    factory { RegisterDataSource() }


    factory {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(androidContext().resources.getString(R.string.google_web_client_id))
            .requestEmail()
            .build()

        GoogleSignIn.getClient(androidContext(), gso)
    }
}

