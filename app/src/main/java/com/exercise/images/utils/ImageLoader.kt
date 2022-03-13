package com.exercise.images.utils

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.davemorrissey.labs.subscaleview.ImageSource
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView
import com.google.android.material.progressindicator.CircularProgressIndicator


object ImageLoader {


    fun loadScalableImage(
        imageUrl: String?,
        imageViewToLoad: SubsamplingScaleImageView,
        placeHolderId: Int = 0,
    ) {
        if(imageUrl!=null) {
            Glide.with(imageViewToLoad.context)
                .asBitmap()
                .load(imageUrl)
                .into(object : CustomTarget<Bitmap>() {
                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: com.bumptech.glide.request.transition.Transition<in Bitmap>?
                    ) {
                        imageViewToLoad.setImage(ImageSource.bitmap(resource))
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {
                        Log.d(ApiConstants.TAG, "onLoadCleared")
                    }
                })
        }

    }
    fun loadImage(
        imageUrl: String?,
        imageViewToLoad: ImageView,
        placeHolderId: Int = 0,
    ) {
        if(imageUrl!=null) {
            Glide.with(imageViewToLoad.context)
                .asBitmap()
                .load(imageUrl)
                .into(object : CustomTarget<Bitmap>() {
                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: com.bumptech.glide.request.transition.Transition<in Bitmap>?
                    ) {
                        imageViewToLoad.setImageBitmap(resource)

                    }

                    override fun onLoadCleared(placeholder: Drawable?) {
                        imageViewToLoad.setImageDrawable(placeholder)
                    }
                })
        } else {
            // make sure Glide doesn't load anything into this view until told otherwise
            Glide.with(imageViewToLoad.context).clear(imageViewToLoad)
            // remove the placeholder (optional); read comments below
            imageViewToLoad.setImageDrawable(null)
        }

    }



}
