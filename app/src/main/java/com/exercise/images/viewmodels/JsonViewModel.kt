package com.exercise.images.viewmodels

import androidx.lifecycle.*
import com.exercise.images.model.DataObject


import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class JsonViewModel (private val json: DataObject) : ViewModel() {



   private val mutableStateFlow = MutableStateFlow(DataObject())
  val items:StateFlow<DataObject> = mutableStateFlow

    init {
        viewModelScope.launch {
            mutableStateFlow.emit(json)

        }
    }

}