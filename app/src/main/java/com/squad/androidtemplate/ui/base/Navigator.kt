package com.squad.androidtemplate.ui.base

import android.app.Activity
import android.content.Intent
import com.squad.androidtemplate.ui.HomeActivity

/**
 * Created by apple on 4/3/17.
 */

class Navigator {

    private var context: Activity? = null

    fun navigateToHomeActivity() {
        val intent = Intent(context, HomeActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
        context?.startActivity(intent)
    }

    fun setContext(context: Activity) {
        this.context = context
    }

}
