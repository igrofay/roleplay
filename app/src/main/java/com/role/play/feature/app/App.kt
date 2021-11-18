package com.role.play.feature.app

import android.app.Application
import android.content.Context

class App : Application() {
    companion object {
        const val tagDatabase = "AppDatabase"
        lateinit var appContext: Context
    }
    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }
}