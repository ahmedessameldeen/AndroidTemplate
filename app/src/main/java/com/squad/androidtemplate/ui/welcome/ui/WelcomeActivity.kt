package com.squad.androidtemplate.ui.welcome.ui

import android.os.Bundle
import com.squad.androidtemplate.R
import com.squad.androidtemplate.databinding.WelcomeActivityBinding
import com.squad.androidtemplate.ui.base.view.BaseActivity
import com.squad.androidtemplate.ui.welcome.ui.welcome.WelcomeFragment
import com.squad.androidtemplate.ui.welcome.ui.welcome.WelcomeViewModel
import com.squad.androidtemplate.utils.extension.replaceFragmentInActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class WelcomeActivity : BaseActivity() {

    private val welcomeViewModel: WelcomeViewModel by viewModel()
    private lateinit var binding: WelcomeActivityBinding

    override fun initVMObservers() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.welcome_activity)
        replaceFragmentInActivity(obtainViewFragment(), R.id.contentFrame)
    }

    private fun obtainViewFragment() =
        supportFragmentManager.findFragmentById(R.id.contentFrame) ?: WelcomeFragment.newInstance()

    companion object {
        private val TAG = WelcomeActivity::class.qualifiedName
    }
}

