package com.gamiphy.library

import android.content.Context
import com.gamiphy.library.callback.OnAuthTrigger
import com.gamiphy.library.models.CoreConfig
import com.gamiphy.library.models.GamiphyEnvironment
import com.gamiphy.library.models.User

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