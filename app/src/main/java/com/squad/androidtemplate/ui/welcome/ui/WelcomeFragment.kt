package com.squad.androidtemplate.ui.welcome.ui.welcome

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.lifecycle.Observer
import com.facebook.login.LoginManager
import com.google.android.material.snackbar.Snackbar
import com.squad.androidtemplate.R
import com.squad.androidtemplate.databinding.WelcomeFragmentBinding
import com.squad.androidtemplate.ui.base.view.BaseFragment
import com.squad.androidtemplate.ui.login.ui.LoginActivity
import com.squad.androidtemplate.ui.register.ui.register.RegisterActivity
import com.squad.androidtemplate.ui.welcome.ui.WelcomeActivity
import com.squad.androidtemplate.ui.welcome.ui.WelcomeUserActionsListener
import com.squad.androidtemplate.utils.setupSnackbar
import java.util.*

class WelcomeFragment : BaseFragment() {

    private lateinit var binding: WelcomeFragmentBinding

    override fun onBackPressed() {

    }

    override fun setUp() {
        binding.viewmodel?.initFaceBook()
        subscribeUi()
        setupViewModelObserver()
    }

    fun setupViewModelObserver() {

        binding.viewmodel?.uiState?.observe(this, Observer {
            val uiModel = it ?: return@Observer

            if (uiModel.showProgress) {

            }

            if (uiModel.showError != null) {

            }
            if (uiModel.showSuccess != null) {

            }
        })
    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        Snackbar.make(binding.container, errorString, Snackbar.LENGTH_SHORT).show()
        navigateToWelcomeActivity()
    }

    private fun navigateToWelcomeActivity() {
        (activity as WelcomeActivity).navigateToHome()
    }

    private fun subscribeUi() {
        //this sets the LifeCycler owner and receiver
        binding.viewmodel?.startActivityForResultEvent?.setEventReceiver(this, activity as WelcomeActivity)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        binding.viewmodel?.onResultFromActivity(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewmodel?.let {
            view?.setupSnackbar(this, binding.viewmodel!!._retry, it.snackbarMessage, Snackbar.LENGTH_LONG)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.welcome_fragment, container, false)
        binding = WelcomeFragmentBinding.bind(root).apply {
            viewmodel = (activity as WelcomeActivity).obtainViewModel()
            listener = object : WelcomeUserActionsListener {
                override fun onLoginButtonClicked() {
                    startActivity(Intent(activity, LoginActivity::class.java))
                }

                override fun onRegisterButtonClicked() {
                    startActivity(Intent(activity, RegisterActivity::class.java))
                }

                override fun onFacebookButtonClicked() {
                    LoginManager.getInstance()
                        .logInWithReadPermissions(this@WelcomeFragment, Arrays.asList("email", "public_profile"))

                }

                override fun onGoogleButtonClicked() {
                    binding.viewmodel?.googleSignUp()
                }

            }
        }
        binding.lifecycleOwner = this.viewLifecycleOwner
        setHasOptionsMenu(true)
        retainInstance = false
        setUp()
        return binding.root
    }

    companion object {
        private val TAG = WelcomeFragment::class.qualifiedName
        fun newInstance() = WelcomeFragment()
    }


}