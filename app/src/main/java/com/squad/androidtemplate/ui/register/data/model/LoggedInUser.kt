package com.squad.androidtemplate.ui.register.data.model

/**
 * Data class that captures user information for logged in users retrieved from RegisterRepository
 */
data class LoggedInUser(
    val userId: String,
    val displayName: String
)
