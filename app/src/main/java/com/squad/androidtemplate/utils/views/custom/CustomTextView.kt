package com.squad.androidtemplate.utils.views.custom

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.squad.androidtemplate.utils.font.CustomFont

class CustomTextView : AppCompatTextView {

    constructor(context: Context) : super(context) {
        CustomFont.init(this, context, null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        CustomFont.init(this, context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        CustomFont.init(this, context, attrs)
    }
}