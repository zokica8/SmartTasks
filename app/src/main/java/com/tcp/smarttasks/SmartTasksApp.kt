package com.tcp.smarttasks

import android.app.Application
import com.tcp.smarttasks.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class SmartTasksApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@SmartTasksApp)
            androidLogger()

            modules(appModule)
        }
    }
}