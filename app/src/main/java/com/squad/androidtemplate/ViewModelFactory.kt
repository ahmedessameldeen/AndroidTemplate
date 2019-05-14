package com.squad.androidtemplate

import android.annotation.SuppressLint
import android.app.Application
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.squad.androidtemplate.ui.login.data.LoginRepository
import com.squad.androidtemplate.ui.login.ui.LoginViewModel
import com.squad.androidtemplate.ui.welcome.data.WelcomeRepository
import com.squad.androidtemplate.ui.welcome.ui.welcome.WelcomeViewModel
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
class ViewModelFactory private constructor(
    private val loginRepository: LoginRepository,
    private val welcomeRepository: WelcomeRepository

) : ViewModelProvider.NewInstanceFactory(), KoinComponent {

    private val googleSignInClient: GoogleSignInClient by inject()

    override fun <T : ViewModel> create(modelClass: Class<T>) =
        with(modelClass) {
            when {
                isAssignableFrom(LoginViewModel::class.java) ->
                    LoginViewModel(loginRepository)
                isAssignableFrom(WelcomeViewModel::class.java) ->
                    WelcomeViewModel(welcomeRepository, loginRepository, googleSignInClient)
                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T

    companion object {

        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance(application: Application) =
            INSTANCE
                ?: synchronized(ViewModelFactory::class.java) {
                    INSTANCE
                        ?: ViewModelFactory(
                            Injection.provideLoginRepository(application.applicationContext),
                            Injection.provideWelcomeRepository(application.applicationContext)
                        )
                            .also { INSTANCE = it }
                }


        @VisibleForTesting
        fun destroyInstance() {
            INSTANCE = null
        }
    }

}
