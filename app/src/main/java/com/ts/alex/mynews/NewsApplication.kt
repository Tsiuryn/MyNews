package com.ts.alex.mynews

import android.app.Application
import com.ts.alex.mynews.data.local.database.NewsDataBase
import com.ts.alex.mynews.di.singleModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class NewsApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        NewsDataBase.init(applicationContext)
        startKoin {
            androidContext(applicationContext)
            androidLogger(Level.DEBUG)
            modules(listOf(singleModule))
        }
    }
}