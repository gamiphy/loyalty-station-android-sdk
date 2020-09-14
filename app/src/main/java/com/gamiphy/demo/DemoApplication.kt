package com.gamiphy.demo

import android.app.Application
import android.util.Log
import com.gamiphy.loyaltyStation.Config
import com.gamiphy.loyaltyStation.LoyaltyStation
import com.gamiphy.loyaltyStation.models.AuthListener
import com.gamiphy.loyaltyStation.models.User


class DemoApplication() : Application() {
    override fun onCreate() {
        super.onCreate()
        LoyaltyStation.getInstance().init(
            this, config = Config(
                app = "5f040d7cdbd59b001804c401", user = User(
                    firstName = "Riyad",
                    lastName = "Yahya",
                    email = "riyad@gamiphy.co",
                    hash = "93b2844c18a8804dee394ee73efde8294ad263c1488ab8522d93dfd55dcb65fb"
                )
            )
        )
        LoyaltyStation.getInstance().addOnAuthListener(object : AuthListener {
            override fun onAuthTrigger(isSignUp: Boolean) {
                // make your action here, you may start login activity
                Log.d("onAuthTrigger", "$isSignUp")
            }
        })
    }
}