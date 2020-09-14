package com.gamiphy.loyaltyStation.jsSdk

import android.webkit.JavascriptInterface
import com.gamiphy.loyaltyStation.jsSdk.models.JsSdkConfig
import com.gamiphy.loyaltyStation.models.AuthListener
import com.gamiphy.loyaltyStation.models.Environments
import com.google.gson.Gson

class JsSdkImp(override var config: JsSdkConfig) : JsSdk {
    override var authListener: AuthListener? = null

    override fun getUrl(): String {
        return when (this.config.environment) {
            Environments.DEV -> "https://static-dev.gamiphy.co/sdk/android.html"
            Environments.PROD -> "https://sdk.gamiphy.co"
        }
    }

    override fun getInitScript(): String {
        val initConfig = Gson().toJson(config.initConfig).toString()
        val json = initConfig.substring(1, initConfig.length - 1)
        return ("javascript: window.Gamiphy.init({\n" +
                "$json,\n" +
                "goToAuth: function (event) {" +
                JsSdk.JAVASCRIPT_OBJ + "." + JsSdk::authTrigger.name + "(JSON.stringify(event)); " +
                "}" +
                "})"
                )
    }

    @JavascriptInterface
    override fun authTrigger(isSignUp: Boolean) {
        if (this.authListener !== null) {
            this.authListener!!.onAuthTrigger(isSignUp)
        }
    }
}


