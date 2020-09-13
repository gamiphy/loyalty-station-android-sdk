package com.gamiphy.loyaltystation.loyaltystationsdk

import android.content.Context
import com.gamiphy.loyaltystation.loyaltystationsdk.callback.OnAuthTrigger
import com.gamiphy.loyaltystation.loyaltystationsdk.models.CoreConfig
import com.gamiphy.loyaltystation.loyaltystationsdk.models.GamiphyEnvironment
import com.gamiphy.loyaltystation.loyaltystationsdk.models.User

interface LoyaltyStation {
    fun init(context: Context, config: CoreConfig): LoyaltyStation
    fun setEnvironment(env: GamiphyEnvironment)
    fun open(context: Context)
    fun close()
    fun login(user: User)
    fun logout(context: Context)
    fun addOnAuthListener(onAuthTrigger: OnAuthTrigger)

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