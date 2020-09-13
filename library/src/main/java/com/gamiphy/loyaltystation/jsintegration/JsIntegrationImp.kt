package com.gamiphy.loyaltystation.jsintegration

import android.webkit.JavascriptInterface
import com.gamiphy.loyaltystation.loyaltystationsdk.callback.OnAuthTrigger

class JsIntegrationImp : JsIntegration {

    @JavascriptInterface
    override fun isLoggedIn(isLogIn: Boolean) {
        authTrigger(isLogIn)
    }

    fun authTrigger(isLogIn: Boolean) {
        onAuthTriggerListeners.forEach {
            it.onAuthTrigger(isLogIn)
        }
    }

    companion object {
        val onAuthTriggerListeners = mutableListOf<OnAuthTrigger>()
    }
}


