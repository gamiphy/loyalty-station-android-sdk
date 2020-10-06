package com.gamiphy.demo

import android.app.Application
import android.util.Log
import com.gamiphy.loyaltyStation.LoyaltyStation
import com.gamiphy.loyaltyStation.models.Agents
import com.gamiphy.loyaltyStation.models.Config
import com.gamiphy.loyaltyStation.models.Listener
import com.gamiphy.loyaltyStation.models.User

class DemoApplication() : Application() {
    override fun onCreate() {
        super.onCreate()
        LoyaltyStation.init(
            this,
            Config(
                "5f71e34bdbaa0b0019df9c58",
                User(
                    firstName = "Riyad",
                    lastName = "Yahya",
                    email = "riyad@gamiphy.co",
                    hash = "cd479ab925badca85fa44f806fa5066b2f7375e6b4ca2089e92824db0a805c43"
                ),
                Agents.Floward
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