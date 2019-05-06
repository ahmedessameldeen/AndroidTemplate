package com.squad.androidtemplate.ui.welcome.ui.welcome


import com.squad.androidtemplate.ui.base.BaseViewModel
import com.squad.androidtemplate.ui.login.data.WelcomeRepository
import com.squad.androidtemplate.utils.views.custom.ViewCallback


class WelcomeViewModel(private val welcomeRepository: WelcomeRepository) : BaseViewModel() {


    override fun onRefresh() {

    }

    private lateinit var callbackView: ViewCallback

    fun setListener(viewCallback: ViewCallback) {
        callbackView = viewCallback
    }


}
