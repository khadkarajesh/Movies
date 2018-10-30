package com.crushcoder.kmovies

import android.app.Application
import com.crushcoder.kmovies.di.appModule
import com.crushcoder.kmovies.di.networkModule
import org.koin.android.ext.android.startKoin

class App : Application() {
    companion object {
        lateinit var app: Application
        fun get(): Application {
            return app
        }
    }

    override fun onCreate() {
        super.onCreate()
        app = this
        startKoin(listOf(appModule, networkModule))
    }
}