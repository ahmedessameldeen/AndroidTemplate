package com.squad.androidtemplate.ui.welcome.ui

import android.content.Intent

interface ActivityNavigation {
    fun startActivityForResult(intent: Intent?, requestCode: Int)
}