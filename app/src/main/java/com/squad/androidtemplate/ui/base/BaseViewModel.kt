package com.squad.androidtemplate.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    val _snackbarText = MutableLiveData<Pair<Int, Int>>()
    val snackbarMessage: LiveData<Pair<Int, Int>>
        get() = _snackbarText

    val _retry = MutableLiveData<Boolean>()
    val retry: LiveData<Boolean>
        get() = _retry

    val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> get() = _dataLoading

    val _hasMorePosts = MutableLiveData<Boolean>()
    val hasMorePosts: LiveData<Boolean> get() = _hasMorePosts

    val _isFreshData = MutableLiveData<Boolean>()
    val isFreshData: LiveData<Boolean> get() = _isFreshData


    abstract fun onRefresh()

}
