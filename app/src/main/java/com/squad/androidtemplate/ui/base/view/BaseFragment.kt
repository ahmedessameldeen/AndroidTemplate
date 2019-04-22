package com.squad.androidtemplate.ui.base.view

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import com.google.android.material.snackbar.Snackbar
import com.squad.androidtemplate.utils.CommonUtil
import com.squad.androidtemplate.utils.views.custom.ViewCallback


/**
 * <h1>BaseFragment</h1>
 *
 * An abstract base Fragment provide abstraction for child fragments
 *
 * extends
 * @see androidx.fragment.app.Fragment
 *
 * implements
 * @see MVPView
 *
 * @author Ahmed Salah
 * @since 11/11/18.
 */
abstract class BaseFragment : Fragment(), BaseView, ViewCallback {
    override fun noNetwork() {
    }

    override fun connectionTimeOut() {
    }

    private var parentActivity: BaseActivity? = null
    private var progressDialog: ProgressDialog? = null
    private var mSnackbarNoConnection: Snackbar? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity) {
            val activity = context as BaseActivity?
            this.parentActivity = activity
            activity?.onFragmentAttached()
        }

    }

    override fun showLoading() {

    }

    override fun hideLoading() {
    }

    override fun noNetwork(snackbarEvent: LiveData<Int>) {
    }

    override fun connectionTimeOut(snackbarEvent: LiveData<Int>) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun hideProgress() {
        if (progressDialog != null && progressDialog?.isShowing!!) {
            progressDialog?.cancel()
        }
    }

    override fun showProgress() {
        hideProgress()
        progressDialog = CommonUtil.showLoadingDialog(this.context)
    }

    fun getBaseActivity() = parentActivity


    interface CallBack {
        fun onFragmentAttached()
        fun onFragmentDetached(tag: String)
    }

    abstract fun onBackPressed()
    abstract fun setUp()
    abstract fun observeOnVM()
}

interface Action {

     fun perform()

}