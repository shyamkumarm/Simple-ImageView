package com.exercise.images.modules



import android.app.Application
import com.exercise.images.model.DataObject
import com.google.gson.Gson


import org.koin.dsl.module






private  fun getJsonClass(app:Application): DataObject =  Gson().fromJson(app.assets.open("api.json").bufferedReader().use { it.readText() },DataObject::class.java)

val jsonModule = module {
    single { getJsonClass(get()) }
}

val jsonModules = listOf(jsonModule)