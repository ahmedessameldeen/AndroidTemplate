package com.squad.androidtemplate.ui.welcome.ui


import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.Profile
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
import com.squad.androidtemplate.utils.views.custom.ViewCallback

const val GOOGLE_SIGN_IN: Int = 9001
const val FACEBOOK_SIGN_IN: Int = 64206

class WelcomeViewModel(
    private val welcomeRepository: WelcomeRepository,
    private val loginRepository: LoginRepository,
    private val googleSignInClient: GoogleSignInClient
) : BaseViewModel() {

    val startActivityForResultEvent = LiveMessageEvent<WelcomeNavigator>()

    private val _uiState = MutableLiveData<WelcomeUiModel>()
    val uiState: LiveData<WelcomeUiModel>
        get() = _uiState

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
                val profile = Profile.getCurrentProfile()
                emitUiState(
                    showSuccess = Event(
                        LoginResultUiModel(
                            profile.firstName,
                            profile.getProfilePictureUri(300, 300).toString()
                        )
                    )
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
                    showSuccess = Event(
                        LoginResultUiModel(
                            completedTask.result?.displayName!!,
                            completedTask.result?.email
                        )
                    )
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
        showSuccess: Event<LoginResultUiModel>? = null,
        enableLoginButton: Boolean = false
    ) {
        val uiModel = WelcomeUiModel(showProgress, showError, showSuccess, enableLoginButton)
        _uiState.value = uiModel
    }

    override fun onRefresh() {

    }

    private lateinit var callbackView: ViewCallback

    fun setListener(viewCallback: ViewCallback) {
        callbackView = viewCallback
    }


}

data class WelcomeUiModel(
    val showProgress: Boolean,
    val showError: Event<Int>?,
    val showSuccess: Event<LoginResultUiModel>?,
    val enableLoginButton: Boolean
)


/**
 * UI Model for login success
 */
data class LoginResultUiModel(
    val displayName: String,
    val portraitUrl: String?
)