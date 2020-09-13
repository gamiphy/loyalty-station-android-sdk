package com.gamiphy.gamiphysdk

import android.app.Application
import com.gamiphy.loyaltystation.loyaltystationsdk.LoyaltyStation
import com.gamiphy.loyaltystation.loyaltystationsdk.models.CoreConfig
import com.gamiphy.loyaltystation.loyaltystationsdk.models.GamiphyEnvironment


class DemoApplication() : Application() {

    override fun onCreate() {
        super.onCreate()
        LoyaltyStation.getInstance()
            .init(this, config = CoreConfig(app = "5f040d7cdbd59b001804c401"))
            .setEnvironment(GamiphyEnvironment.DEV)
    }
}