package com.gamiphy.demo

import android.app.Application
import android.util.Log
import com.gamiphy.loyaltyStation.LoyaltyStation
import com.gamiphy.loyaltyStation.models.OnAuthTriggerListener
import com.gamiphy.loyaltyStation.models.User

class DemoApplication() : Application() {
    override fun onCreate() {
        super.onCreate();

        LoyaltyStation
            .setApp("5f71e34bdbaa0b0019df9c58")
            .setAgent("floward")
            .setLanguage("en_US")
            .setUser(
                User(
                    id = "test-id",
                    country = "ps",
                    firstName = "Riyad",
                    lastName = "Yahya",
                    hash = "237ccb1812cf2c893e341788921ec62515ca6d0507d7e4577055b25b794f831c"
                )
            )
            .setSandbox(true)
            .setOnAuthTriggerListener(object : OnAuthTriggerListener {
                override fun onAuthTrigger(isSignUp: Boolean) {
                    // make your action here, you may start login activity
                    Log.d("onAuthTrigger", "$isSignUp")
                }
            })
            .initialize(this)
    }
}