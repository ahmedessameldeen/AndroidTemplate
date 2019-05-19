package com.squad.androidtemplate.ui.register.ui.register

/**
 * Data validation state of the register form.
 */
data class RegisterFormState(
    val usernameError: Int? = null,
    val passwordError: Int? = null,
    val isDataValid: Boolean = false
)
