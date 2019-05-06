package com.squad.androidtemplate.ui.register.ui.register

import android.os.Bundle
import com.squad.androidtemplate.R
import com.squad.androidtemplate.databinding.RegisterActivityBinding
import com.squad.androidtemplate.ui.base.view.BaseActivity
import com.squad.androidtemplate.utils.extension.replaceFragmentInActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class RegisterActivity : BaseActivity() {

    private val registerViewModel: RegisterViewModel by viewModel()
    private lateinit var binding: RegisterActivityBinding

    override fun initVMObservers() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_activity)
        replaceFragmentInActivity(obtainViewFragment(), R.id.contentFrame)
    }

    private fun obtainViewFragment() =
        supportFragmentManager.findFragmentById(R.id.contentFrame) ?: RegisterFragment.newInstance()

    companion object {
        private val TAG = RegisterActivity::class.qualifiedName
    }
}

