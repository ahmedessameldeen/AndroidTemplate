package com.squad.androidtemplate.ui.login.ui.login

import android.os.Bundle
import com.squad.androidtemplate.R
import com.squad.androidtemplate.databinding.LoginActivityBinding
import com.squad.androidtemplate.ui.base.view.BaseActivity
import com.squad.androidtemplate.utils.extension.obtainViewModel
import com.squad.androidtemplate.utils.extension.replaceFragmentInActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class LoginActivity : BaseActivity() {

    private val loginViewModel: LoginViewModel by viewModel()
    private lateinit var binding: LoginActivityBinding

    override fun initVMObservers() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
        replaceFragmentInActivity(obtainViewFragment(), R.id.contentFrame)
    }

    private fun obtainViewFragment() =
        supportFragmentManager.findFragmentById(R.id.contentFrame) ?: LoginFragment.newInstance()

    fun obtainViewModel(): LoginViewModel = obtainViewModel(LoginViewModel::class.java)

    companion object {
        private val TAG = LoginActivity::class.qualifiedName
    }
}

