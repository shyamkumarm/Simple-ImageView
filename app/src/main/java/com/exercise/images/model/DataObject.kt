package com.exercise.images.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

class DataObject : ArrayList<DataObject.DataObjectItem>(){
    @Parcelize
    data class DataObjectItem(
        val copyright: String?,
        val date: String,
        val explanation: String,
        val hdurl: String,
        val media_type: String,
        val service_version: String,
        val title: String,
        val url: String
    ): Parcelable
}

