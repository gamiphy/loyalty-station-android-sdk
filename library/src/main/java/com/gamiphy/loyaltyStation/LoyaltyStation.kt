package com.gamiphy.loyaltyStation

import android.content.Context
import com.gamiphy.loyaltyStation.models.Config
import com.gamiphy.loyaltyStation.models.Listener
import com.gamiphy.loyaltyStation.models.User

interface LoyaltyStation {
    fun init(context: Context, config: Config, listener: Listener): LoyaltyStation
    fun open(context: Context)
    fun close()
    fun login(user: User)
    fun logout()

    companion object {
        private var instance: LoyaltyStationImpl? = null

        fun getInstance(): LoyaltyStation {
            if (instance == null) {
                instance = LoyaltyStationImpl()
            }
            return instance!!
        }
    }
}