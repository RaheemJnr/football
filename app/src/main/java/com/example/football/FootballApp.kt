package com.example.football

import android.app.Application
import com.example.football.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class FootballApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@FootballApp)
            modules(appModule)
        }
    }
}
