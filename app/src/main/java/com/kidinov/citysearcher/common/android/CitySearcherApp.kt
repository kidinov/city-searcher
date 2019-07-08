package com.kidinov.citysearcher.common.android

import android.app.Application
import com.kidinov.citysearcher.common.servicelocator.appModule
import com.kidinov.citysearcher.feature.cities.citiesModule
import com.kidinov.citysearcher.feature.splash.splashModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CitySearcherApp : Application() {
    override fun onCreate() {
        super.onCreate()

        doInject()
    }

    private fun doInject() {
        startKoin {
            androidContext(this@CitySearcherApp)
            modules(listOf(appModule, splashModule, citiesModule))
        }
    }
}