package com.squad.androidtemplate.utils.extension

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.squad.androidtemplate.R

internal fun FragmentManager.removeFragment(
    tag: String,
    slideIn: Int = R.anim.slide_left,
    slideOut: Int = R.anim.slide_right) {
    this.findFragmentByTag(tag)?.let {
        this.beginTransaction()
            .disallowAddToBackStack()
            .remove(it)
            .commitNow()
    }
}

internal fun FragmentManager.addFragment(
    containerViewId: Int,
    fragment: Fragment,
    tag: String,
    slideIn: Int = R.anim.slide_left,
    slideOut: Int = R.anim.slide_right
) {
    this.beginTransaction().addToBackStack(null)
        .replace(containerViewId, fragment, tag)
        .commit()
}

