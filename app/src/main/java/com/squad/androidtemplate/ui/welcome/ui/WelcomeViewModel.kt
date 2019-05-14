package com.squad.androidtemplate.ui.welcome.ui.welcome


import android.content.Intent
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.squad.androidtemplate.Event
import com.squad.androidtemplate.R
import com.squad.androidtemplate.ui.base.BaseViewModel
import com.squad.androidtemplate.ui.login.data.LoginRepository
import com.squad.androidtemplate.ui.welcome.data.WelcomeRepository
import com.squad.androidtemplate.ui.welcome.ui.LiveMessageEvent
import com.squad.androidtemplate.ui.welcome.ui.WelcomeNavigator
import com.squad.androidtemplate.utils.views.custom.ViewCallback

const val GOOGLE_SIGN_IN: Int = 9001
const val FACEBOOK_SIGN_IN: Int = 64206

class WelcomeViewModel(
    private val welcomeRepository: WelcomeRepository,
    private val loginRepository: LoginRepository,
    private val googleSignInClient: GoogleSignInClient
) : BaseViewModel() {

    val startActivityForResultEvent = LiveMessageEvent<WelcomeNavigator>()
    private var mCallbackManager: CallbackManager? = null
    //Called on google login button click
    fun googleSignUp() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResultEvent.sendEvent { startActivityForResult(signInIntent, GOOGLE_SIGN_IN) }
    }

    fun initFaceBook() {
        mCallbackManager = CallbackManager.Factory.create()
        LoginManager.getInstance().registerCallback(mCallbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                emitUiState(
                    showSuccess = Event(R.string.login_successful)
                )
            }

            override fun onCancel() {
                emitUiState(
                    showError = Event(R.string.login_canceled)
                )
            }

            override fun onError(error: FacebookException) {
                emitUiState(
                    showError = Event(R.string.login_failed)
                )
            }
        })
    }

    //Called from Activity receving result
    fun onResultFromActivity(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            GOOGLE_SIGN_IN -> {
                val task = GoogleSignIn.getSignedInAccountFromIntent(data)
                googleSignInComplete(task)
            }
            FACEBOOK_SIGN_IN -> {
                mCallbackManager?.onActivityResult(requestCode, resultCode, data)
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
