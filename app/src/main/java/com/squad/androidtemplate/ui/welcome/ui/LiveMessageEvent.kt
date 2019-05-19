package com.squad.androidtemplate.ui.welcome.ui

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.squad.androidtemplate.ui.base.SingleLiveEvent

class LiveMessageEvent<T> : SingleLiveEvent<(T.() -> Unit)?>() {

    fun setEventReceiver(owner: LifecycleOwner, receiver: T) {
        observe(owner, Observer { event ->
            if (event != null) {
                receiver.event()
            }
        })
    }

    fun sendEvent(event: (T.() -> Unit)?) {
        value = event
    }
}