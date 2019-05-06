package com.squad.androidtemplate.ui.welcome.ui.welcome


import android.content.Intent
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.squad.androidtemplate.Event
import com.squad.androidtemplate.R
import com.squad.androidtemplate.ui.base.BaseViewModel
import com.squad.androidtemplate.ui.login.data.WelcomeRepository
import com.squad.androidtemplate.ui.welcome.ui.ActivityNavigation
import com.squad.androidtemplate.ui.welcome.ui.LiveMessageEvent
import com.squad.androidtemplate.utils.views.custom.ViewCallback

const val GOOGLE_SIGN_IN: Int = 9001

class WelcomeViewModel(
    private val welcomeRepository: WelcomeRepository,
    private val googleSignInClient: GoogleSignInClient
) : BaseViewModel() {

    val startActivityForResultEvent = LiveMessageEvent<ActivityNavigation>()

    //Called on google login button click
    fun googleSignUp() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResultEvent.sendEvent { startActivityForResult(signInIntent, GOOGLE_SIGN_IN) }
    }

    //Called from Activity receving result
    fun onResultFromActivity(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            GOOGLE_SIGN_IN -> {
                val task = GoogleSignIn.getSignedInAccountFromIntent(data)
                googleSignInComplete(task)
            }
        }
    }

    private fun googleSignInComplete(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            account?.apply {
                // .. Store user details
                emitUiState(
                    showSuccess = Event(R.string.login_successful)
                )
            }
        } catch (e: ApiException) {
            emitUiState(
                showError = Event(R.string.login_failed)
            )
        }
    }

    private fun emitUiState(
        showProgress: Boolean = false,
        showError: Event<Int>? = null,
        showSuccess: Event<Int>? = null,
        enableLoginButton: Boolean = false
    ) {

    }

    override fun onRefresh() {

    }

    private lateinit var callbackView: ViewCallback

    fun setListener(viewCallback: ViewCallback) {
        callbackView = viewCallback
    }


}
