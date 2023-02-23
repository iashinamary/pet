package com.example.pet.ui

import android.app.Application
import com.example.pet.di.module
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class MyApp: Application() {


    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            modules(module)
            androidContext(this@MyApp)
        }
    }
}