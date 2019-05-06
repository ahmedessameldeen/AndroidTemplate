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
import com.squad.androidtemplate.utils.extension.afterTextChanged
import com.squad.androidtemplate.utils.setupSnackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : BaseFragment() {

    private val viewmodel: LoginViewModel by viewModel()

    private lateinit var binding: LoginFragmentBinding

    override fun onBackPressed() {

    }

    override fun setUp() {

        viewmodel.loginFormState.observe(this@LoginFragment, Observer {
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

        viewmodel.loginResult.observe(this@LoginFragment, Observer {
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

        binding.username.afterTextChanged {
            viewmodel.loginDataChanged(
                binding.username.text.toString(),
                binding.password.text.toString()
            )
        }

        binding.password.apply {
            afterTextChanged {
                viewmodel.loginDataChanged(
                    binding.username.text.toString(),
                    binding.password.text.toString()
                )
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        viewmodel.login(
                            binding.username.text.toString(),
                            binding.password.text.toString()
                        )
                }
                false
            }

            binding.login.setOnClickListener {
                binding.loading.visibility = View.VISIBLE
                viewmodel.login(binding.username.text.toString(), binding.password.text.toString())
            }
        }
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
        val root = inflater.inflate(R.layout.login_fragment, container, false)
        binding = LoginFragmentBinding.bind(root)
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