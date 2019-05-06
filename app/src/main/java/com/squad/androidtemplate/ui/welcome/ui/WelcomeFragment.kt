package com.squad.androidtemplate.ui.welcome.ui.welcome

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import com.squad.androidtemplate.R
import com.squad.androidtemplate.databinding.WelcomeFragmentBinding
import com.squad.androidtemplate.ui.base.view.BaseFragment
import com.squad.androidtemplate.ui.login.ui.login.LoginActivity
import com.squad.androidtemplate.ui.register.ui.register.RegisterActivity
import com.squad.androidtemplate.utils.setupSnackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class WelcomeFragment : BaseFragment() {

    private val viewmodel: WelcomeViewModel by viewModel()

    private lateinit var binding: WelcomeFragmentBinding

    override fun onBackPressed() {

    }

    override fun setUp() {
        binding.login.setOnClickListener {
            startActivity(Intent(activity, LoginActivity::class.java))
        }
        binding.register.setOnClickListener {
            startActivity(Intent(activity, RegisterActivity::class.java))
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