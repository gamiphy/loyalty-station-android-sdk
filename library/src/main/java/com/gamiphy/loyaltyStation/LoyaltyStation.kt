package com.gamiphy.loyaltyStation

import android.content.Context
import com.gamiphy.loyaltyStation.models.AuthListener
import com.gamiphy.loyaltyStation.models.User

interface LoyaltyStation {
    fun init(context: Context, config: Config): LoyaltyStation
    fun open(context: Context)
    fun close()
    fun login(user: User)
    fun logout()
    fun addOnAuthListener(authListener: AuthListener)

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