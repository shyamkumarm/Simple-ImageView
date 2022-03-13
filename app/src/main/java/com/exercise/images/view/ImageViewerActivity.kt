package com.exercise.images.view


import android.view.View
import com.exercise.images.base.BaseActivity
import com.exercise.images.databinding.ActivityImageViewerBinding
import com.exercise.images.model.DataObject
import com.exercise.images.utils.ApiConstants
import com.exercise.images.utils.ImageLoader


class ImageViewerActivity : BaseActivity(){
    private val binding by lazy {
        ActivityImageViewerBinding.inflate(layoutInflater)
    }

    override fun getContentLayout(): View = binding.root
    override fun onCreate() {

       val dataObject =  intent.getParcelableExtra<DataObject.DataObjectItem>(ApiConstants.INTENT_DATA)
        setSupportActionBar(binding.toolbar.apply { title = dataObject?.title })
       ImageLoader.loadScalableImage(dataObject?.url,binding.imgViewer)

        binding.info.setOnClickListener {
            val fragment =  ImageInfoFragment.newInstance(dataObject)
            fragment.show(supportFragmentManager, "TAG")
        }

    }

}