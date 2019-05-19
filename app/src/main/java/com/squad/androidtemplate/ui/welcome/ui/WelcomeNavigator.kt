package com.squad.androidtemplate.ui.welcome.ui

import android.content.Intent

interface WelcomeNavigator {
    fun startActivityForResult(intent: Intent?, requestCode: Int)
    fun navigateToHome()
}