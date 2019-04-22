package com.squad.androidtemplate.utils.extension

import android.content.Context
import android.widget.ImageView
import androidx.core.content.res.ResourcesCompat
import com.bumptech.glide.Glide
import com.squad.androidtemplate.R
import com.stfalcon.imageviewer.StfalconImageViewer


internal fun ImageView.loadImage(url: String?) {
    Glide.with(this.context)
        .load(url)
        .asBitmap()
        .placeholder(ResourcesCompat.getDrawable(resources, R.drawable.placeholder, null))
        .centerCrop()
        .into(this)
}

internal fun ImageView.loadImageNormal(url: String?) {
    Glide.with(this.context)
        .load(url)
        .asBitmap()
        .placeholder(ResourcesCompat.getDrawable(resources, R.drawable.placeholder, null))
        .into(this)
}

internal fun ImageView.showImageViewer(context: Context, withTransitionView: ImageView, images: ArrayList<String>) {
    StfalconImageViewer.Builder<String>(context, images)
    { view, image ->
        view.loadImageNormal(image)
    }
        .allowZooming(true)
        .allowSwipeToDismiss(true)
        .withTransitionFrom(withTransitionView)
        .show()
}


