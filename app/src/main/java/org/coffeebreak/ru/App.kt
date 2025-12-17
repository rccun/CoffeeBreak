package org.coffeebreak.ru

import android.app.Application
import com.yandex.mapkit.MapKitFactory
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App: Application() {
    override fun onCreate() {
        super.onCreate()

        MapKitFactory.setApiKey("d2555416-1e4c-4558-a8de-a685c3122bb1")
        MapKitFactory.initialize(this)
    }
}