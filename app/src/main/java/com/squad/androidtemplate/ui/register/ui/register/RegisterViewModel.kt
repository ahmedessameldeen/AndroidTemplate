package com.squad.androidtemplate.ui.register.ui.register

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.squad.androidtemplate.R
import com.squad.androidtemplate.ui.base.BaseViewModel
import com.squad.androidtemplate.ui.register.data.RegisterRepository
import com.squad.androidtemplate.ui.register.data.Result
import com.squad.androidtemplate.utils.views.custom.ViewCallback


class RegisterViewModel(private val registerRepository: RegisterRepository) : BaseViewModel() {


    override fun onRefresh() {

    }

    private lateinit var callbackView: ViewCallback

    fun setListener(viewCallback: ViewCallback) {
        callbackView = viewCallback
    }


    private val _registerForm = MutableLiveData<RegisterFormState>()
    val registerFormState: LiveData<RegisterFormState> = _registerForm

    private val _registerResult = MutableLiveData<RegisterResult>()
    val registerResult: LiveData<RegisterResult> = _registerResult

    fun register(username: String, password: String) {
        // can be launched in a separate asynchronous job
        val result = registerRepository.register(username, password)

        if (result is Result.Success) {
            _registerResult.value = RegisterResult(success = LoggedInUserView(displayName = result.data.displayName))
        } else {
            _registerResult.value = RegisterResult(error = R.string.register_failed)
        }
    }

    fun registerDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _registerForm.value = RegisterFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _registerForm.value = RegisterFormState(passwordError = R.string.invalid_password)
        } else {
            _registerForm.value = RegisterFormState(isDataValid = true)
        }
    }

    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }


}
