package com.squad.androidtemplate.ui.welcome.ui.welcome

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.facebook.login.LoginManager
import com.google.android.material.snackbar.Snackbar
import com.squad.androidtemplate.R
import com.squad.androidtemplate.databinding.WelcomeFragmentBinding
import com.squad.androidtemplate.ui.base.view.BaseFragment
import com.squad.androidtemplate.ui.login.ui.login.LoginActivity
import com.squad.androidtemplate.ui.register.ui.register.RegisterActivity
import com.squad.androidtemplate.ui.welcome.ui.ActivityNavigation
import com.squad.androidtemplate.utils.setupSnackbar
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class WelcomeFragment : BaseFragment(), ActivityNavigation {

    private val viewmodel: WelcomeViewModel by viewModel()

    private lateinit var binding: WelcomeFragmentBinding

    override fun onBackPressed() {

    }

    override fun setUp() {
        setupNormalLoginButton()
        setupNormalRegisterButton()
        setupGoogleLoginButton()
        setupFacebookLoginButton()
        subscribeUi()
    }

    private fun setupGoogleLoginButton() {
        binding.googleSignInButton.setOnClickListener {
            viewmodel.googleSignUp()
        }
    }

    private fun setupFacebookLoginButton() {
        viewmodel.initFaceBook()
        binding.facebookSignInButton.setOnClickListener {
            LoginManager.getInstance()
                .logInWithReadPermissions(this@WelcomeFragment, Arrays.asList("email", "public_profile"))
        }
    }

    private fun setupNormalRegisterButton() {
        binding.register.setOnClickListener {
            startActivity(Intent(activity, RegisterActivity::class.java))
        }
    }

    private fun setupNormalLoginButton() {
        binding.login.setOnClickListener {
            startActivity(Intent(activity, LoginActivity::class.java))
        }
    }

    private fun subscribeUi() {
        //this sets the LifeCycler owner and receiver
        viewmodel.startActivityForResultEvent.setEventReceiver(this, this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        viewmodel.onResultFromActivity(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun observeOnVM() {

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewmodel?.let {
            view?.setupSnackbar(this, viewmodel._retry, it.snackbarMessage, Snackbar.LENGTH_LONG)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.welcome_fragment, container, false)
        binding = WelcomeFragmentBinding.bind(root)
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