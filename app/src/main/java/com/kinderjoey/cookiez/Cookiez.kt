package com.kinderjoey.cookiez

import android.app.Application
import com.kinderjoey.cookiez.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class Cookiez: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@Cookiez)
            modules(
                listOf(
                    databaseModule,
                    firestoreModule,
                    repositoryModule,
                    viewModelModule,
                    adapterModule
                )
            )
        }
    }
}