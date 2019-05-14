package com.squad.androidtemplate.ui.register.ui.register

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
import com.squad.androidtemplate.databinding.RegisterFragmentBinding
import com.squad.androidtemplate.ui.base.view.BaseFragment
import com.squad.androidtemplate.utils.extension.afterTextChanged
import com.squad.androidtemplate.utils.setupSnackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterFragment : BaseFragment() {

    private val viewmodel: RegisterViewModel by viewModel()
    private lateinit var binding: RegisterFragmentBinding

    override fun onBackPressed() {

    }

    override fun setUp() {

        viewmodel.registerFormState.observe(this@RegisterFragment, Observer {
            val registerState = it ?: return@Observer

            // disable register button unless both username / password is valid
            binding.register.isEnabled = registerState.isDataValid

            if (registerState.usernameError != null) {
                binding.username.error = getString(registerState.usernameError)
            }
            if (registerState.passwordError != null) {
                binding.password.error = getString(registerState.passwordError)
            }
        })

        viewmodel.registerResult.observe(this@RegisterFragment, Observer {
            val registerResult = it ?: return@Observer

            binding.loading.visibility = View.GONE
            if (registerResult.error != null) {
                showRegisterFailed(registerResult.error)
            }
            if (registerResult.success != null) {
                updateUiWithUser(registerResult.success)
            }
            activity?.setResult(Activity.RESULT_OK)

            //Complete and destroy register activity once successful
            activity?.finish()
        })

        binding.username.afterTextChanged {
            viewmodel.registerDataChanged(
                binding.username.text.toString(),
                binding.password.text.toString()
            )
        }

        binding.password.apply {
            afterTextChanged {
                viewmodel.registerDataChanged(
                    binding.username.text.toString(),
                    binding.password.text.toString()
                )
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        viewmodel.register(
                            binding.username.text.toString(),
                            binding.password.text.toString()
                        )
                }
                false
            }

            binding.register.setOnClickListener {
                binding.loading.visibility = View.VISIBLE
                viewmodel.register(binding.username.text.toString(), binding.password.text.toString())
            }
        }
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
        val root = inflater.inflate(R.layout.register_fragment, container, false)
        binding = RegisterFragmentBinding.bind(root)
        binding.lifecycleOwner = this.viewLifecycleOwner
        setHasOptionsMenu(true)
        retainInstance = false
        setUp()
        return binding.root
    }

    companion object {
        private val TAG = RegisterFragment::class.qualifiedName
        fun newInstance() = RegisterFragment()
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

    private fun showRegisterFailed(@StringRes errorString: Int) {
        Toast.makeText(activity?.applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }

}