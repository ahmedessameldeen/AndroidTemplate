package com.squad.androidtemplate.ui.login.ui.login

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.squad.androidtemplate.R
import com.squad.androidtemplate.databinding.LoginFragmentBinding
import com.squad.androidtemplate.ui.base.view.BaseFragment
import com.squad.androidtemplate.utils.setupSnackbar

class LoginFragment : BaseFragment() {

    private lateinit var binding: LoginFragmentBinding

    override fun onBackPressed() {

    }

    override fun setUp() {

        binding.viewmodel?.loginFormState?.observe(this@LoginFragment, Observer {
            val loginState = it ?: return@Observer

            // disable login button unless both username / password is valid
            binding.login.isEnabled = loginState.isDataValid

            if (loginState.usernameError != null) {
                binding.username.error = getString(loginState.usernameError)
            }
            if (loginState.passwordError != null) {
                binding.password.error = getString(loginState.passwordError)
            }
        })

        binding.viewmodel?.loginResult?.observe(this@LoginFragment, Observer {
            val loginResult = it ?: return@Observer

            binding.loading.visibility = View.GONE
            if (loginResult.error != null) {
                showLoginFailed(loginResult.error)
            }
            if (loginResult.success != null) {
                updateUiWithUser(loginResult.success)
            }
            activity?.setResult(Activity.RESULT_OK)

            //Complete and destroy login activity once successful
            activity?.finish()
        })

        binding.password.apply {
            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        binding.viewmodel?.login(
                            binding.username.text.toString(),
                            binding.password.text.toString()
                        )
                }
                false
            }

        }
    }

    override fun observeOnVM() {

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
        val root = inflater.inflate(R.layout.login_fragment, container, false)
        binding = LoginFragmentBinding.bind(root).apply {
            viewmodel = (activity as LoginActivity).obtainViewModel()
            listener = object : LoginUserActionsListener {

                override fun onUserInputsChanged(name: String, password: String) {
                    binding.viewmodel?.loginDataChanged(name, password)
                }

                override fun onLoginButtonClicked(name: String, password: String) {
                    binding.loading.visibility = View.VISIBLE
                    binding.viewmodel?.login(name, password)
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
        private val TAG = LoginFragment::class.qualifiedName
        fun newInstance() = LoginFragment()
    }

    private fun updateUiWithUser(model: LoggedInUserView) {
        val welcome = getString(R.string.welcome)
        val displayName = model.displayName
        // TODO : initiate successful logged in experience
        Toast.makeText(
            activity?.applicationContext,
            "$welcome $displayName",
            Toast.LENGTH_LONG
        ).show()
    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        Toast.makeText(activity?.applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }

}