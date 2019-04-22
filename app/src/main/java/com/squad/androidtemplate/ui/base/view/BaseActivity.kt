package com.squad.androidtemplate.ui.base.view

import android.app.ProgressDialog
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.LiveData
import com.squad.androidtemplate.R
import com.squad.androidtemplate.utils.CommonUtil
import com.squad.androidtemplate.utils.views.custom.ViewCallback

/**
 * Created by Ahmed Salah on 04/11/18.
 */
abstract class BaseActivity : AppCompatActivity(),BaseView, BaseFragment.CallBack, ViewCallback {
    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun noNetwork() {
    }

    override fun connectionTimeOut() {
    }

    override fun noNetwork(snackbarEvent: LiveData<Int>) {
    }

    override fun connectionTimeOut(snackbarEvent: LiveData<Int>) {
//        getWindow().getDecorView().getRootView()
    }

    private var progressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Make sure we use vector drawables
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        initVMObservers()
    }

    override fun hideProgress() {
        progressDialog?.let { if (it.isShowing) it.cancel() }
    }

    override fun showProgress() {
        hideProgress()
        progressDialog = CommonUtil.showLoadingDialog(this)
    }

    override fun onFragmentAttached() {

    }

    override fun onFragmentDetached(tag: String) {

    }

    abstract fun initVMObservers()


    fun onBackPressed(view: View) {
        onBackPressed()
    }

}