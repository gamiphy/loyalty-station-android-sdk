package com.gamiphy.gamiphysdk

import android.app.Application
import com.gamiphy.library.LoyaltyStation
import com.gamiphy.library.models.CoreConfig
import com.gamiphy.library.models.GamiphyEnvironment


class DemoApplication() : Application() {

    override fun onCreate() {
        super.onCreate()
        LoyaltyStation.getInstance()
            .init(this, config = CoreConfig(app = "5f040d7cdbd59b001804c401"))
            .setEnvironment(GamiphyEnvironment.DEV)
    }
}