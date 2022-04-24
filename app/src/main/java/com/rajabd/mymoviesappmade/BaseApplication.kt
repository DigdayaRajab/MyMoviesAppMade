package com.rajabd.mymoviesappmade

import android.app.Application
import com.rajabd.core.di.databaseModule
import com.rajabd.core.di.networkModule
import com.rajabd.core.di.repositoryModule
import com.rajabd.mymoviesappmade.di.useCaseModule
import com.rajabd.mymoviesappmade.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@BaseApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}
