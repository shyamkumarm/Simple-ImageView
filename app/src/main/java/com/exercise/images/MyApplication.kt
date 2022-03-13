package com.exercise.images

import android.app.Application
import com.exercise.images.modules.jsonModules
import com.exercise.images.modules.viewModules

import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level


class MyApplication : Application() {


    override fun onCreate() {
        super.onCreate()

         startKoin {
             androidLogger(Level.ERROR)
            androidContext(this@MyApplication)
            modules(jsonModules + viewModules)
        }
    }
}