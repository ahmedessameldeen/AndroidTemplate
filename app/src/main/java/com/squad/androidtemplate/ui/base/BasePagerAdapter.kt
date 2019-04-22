package com.squad.androidtemplate.ui.base

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class BasePagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    var headersList: MutableList<String> = mutableListOf()
    val mFragmentList = arrayListOf<Fragment>()

    override fun getItem(position: Int): Fragment {
        return mFragmentList[position]
    }

    fun addFrag(fragment: Fragment, title: String) {
        mFragmentList.add(fragment)
        headersList.add(title)
    }

    override fun getCount(): Int {
        return headersList.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return headersList.get(position)
    }
}