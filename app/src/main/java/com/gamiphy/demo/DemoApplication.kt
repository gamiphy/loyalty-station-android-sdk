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
                    id = "test-id",
                    firstName = "Riyad",
                    lastName = "Yahya",
                    hash = "1f4279caf6d70aca238e17639a8768b7221ff488e791e79e7f746211b1c15bb2"
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