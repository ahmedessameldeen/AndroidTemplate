package com.squad.androidtemplate.ui.base.view

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.LiveData
import com.google.android.material.snackbar.Snackbar
import com.squad.androidtemplate.R
import com.squad.androidtemplate.ui.base.Navigator
import com.squad.androidtemplate.utils.CommonUtil
import com.squad.androidtemplate.utils.views.custom.ViewCallback

/**
 * Created by Ahmed Salah on 04/11/18.
 */
abstract class BaseActivity : AppCompatActivity(),BaseView, BaseFragment.CallBack, ViewCallback {

    var navigator = Navigator()

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
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        initVMObservers()
        configNavigator()
    }

    override fun hideProgress() {
        progressDialog?.let { if (it.isShowing) it.cancel() }
    }

    override fun showProgress() {
        hideProgress()
        progressDialog = CommonUtil.showLoadingDialog(this)
    }

    fun hideKeyboard() {
        // Check if no view has focus:
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    fun showSnackBar(message: String) {
        val snackbar = Snackbar.make(
            findViewById(android.R.id.content),
            message, Snackbar.LENGTH_LONG
        )
        snackbar.show()
    }

    fun showSnackBar(@StringRes messageId: Int) {
        val snackbar = Snackbar.make(
            findViewById(android.R.id.content),
            messageId, Snackbar.LENGTH_LONG
        )
        snackbar.show()
    }

    fun showSnackBar(view: View, @StringRes messageId: Int) {
        val snackbar = Snackbar.make(view, messageId, Snackbar.LENGTH_LONG)
        snackbar.show()
    }

    fun showToast(@StringRes messageId: Int) {
        Toast.makeText(this, messageId, Toast.LENGTH_LONG).show()
    }

    fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    fun showWarningDialog(messageId: Int) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage(messageId)
        builder.setPositiveButton(R.string.button_ok, null)
        builder.show()
    }

    fun showWarningDialog(message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage(message)
        builder.setPositiveButton(R.string.button_ok, null)
        builder.show()
    }

    fun showNotCancelableWarningDialog(message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage(message)
        builder.setPositiveButton(R.string.button_ok, null)
        builder.setCancelable(false)
        builder.show()
    }

    fun showWarningDialog(message: Int, listener: DialogInterface.OnClickListener) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage(message)
        builder.setPositiveButton(R.string.button_ok, listener)
        builder.show()
    }

    fun showWarningDialog(message: String, listener: DialogInterface.OnClickListener) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage(message)
        builder.setPositiveButton(R.string.button_ok, listener)
        builder.show()
    }

    override fun onFragmentAttached() {

    }

    override fun onFragmentDetached(tag: String) {

    }

    abstract fun initVMObservers()

    private fun configNavigator() {
        navigator.setContext(this)
    }

    fun onBackPressed(view: View) {
        onBackPressed()
    }

}