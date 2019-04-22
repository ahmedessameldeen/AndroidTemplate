package com.squad.androidtemplate.utils.views.custom

import androidx.lifecycle.LiveData

interface   ViewCallback {
    fun showLoading()
    fun hideLoading()
    fun noNetwork(snackbarEvent: LiveData<Int>)
    fun connectionTimeOut(snackbarEvent: LiveData<Int>)
    fun noNetwork()
    fun connectionTimeOut()
}