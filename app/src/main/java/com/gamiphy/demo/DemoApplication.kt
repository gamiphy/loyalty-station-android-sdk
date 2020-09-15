package com.gamiphy.demo

import android.app.Application
import android.util.Log
import com.gamiphy.loyaltyStation.LoyaltyStation
import com.gamiphy.loyaltyStation.models.Config
import com.gamiphy.loyaltyStation.models.Listener
import com.gamiphy.loyaltyStation.models.User

class DemoApplication() : Application() {
    override fun onCreate() {
        super.onCreate()
        LoyaltyStation.init(
            this,
            Config(
                "5f58e6e44e7ab40023173692",
                User(
                    firstName = "Riyad",
                    lastName = "Yahya",
                    email = "adsad@gmailc.om",
                    hash = "df8eed1b4f3e37b9e220d87eab53133174ec1c71d67985f834bd8bbe2e964c60"
                )
            ),
            object : Listener {
                override fun onAuthTrigger(isSignUp: Boolean) {
                    // make your action here, you may start login activity
                    Log.d("onAuthTrigger", "$isSignUp")
                }
            }
        )
    }
}