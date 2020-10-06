package com.gamiphy.loyaltyStation.jsSdk

import android.util.Log
import android.webkit.JavascriptInterface
import com.gamiphy.loyaltyStation.jsSdk.models.JsSdkConfig
import com.gamiphy.loyaltyStation.models.Agents
import com.gamiphy.loyaltyStation.models.Environments
import com.google.gson.Gson

class JsSdkImp(override var config: JsSdkConfig, override var listener: JsListener) : JsSdk {
    private fun getPath(): String {
        return when (this.config.agent) {
            Agents.Default -> "/sdk/android.html"
            Agents.Floward -> "/sdk/custom/floward/index.html"
        }
    }

    private fun getDomain(): String {
        return when (this.config.environment) {
            Environments.DEV -> "https://static-dev.gamiphy.co"
            Environments.STAGING -> "https://static-staging.gamiphy.co"
            Environments.PROD -> "https://sdk.gamiphy.co"
        }
    }

    override fun getUrl(): String {
        return this.getDomain() + this.getPath()
    }

    override fun getInitScript(): String {
        val initConfig = Gson().toJson(config.initConfig).toString()
        val json = initConfig.substring(1, initConfig.length - 1);

        return ("javascript: window.Gamiphy.init({\n" +
                "$json,\n" +
                "goToAuth: function (event) {" +
                JsSdk.JAVASCRIPT_OBJ + "." + JsSdk::authTrigger.name + "(JSON.stringify(event)); " +
                "}," +
                "onClose: function (event) {" +
                JsSdk.JAVASCRIPT_OBJ + "." + JsSdk::onClose.name + "(); " +
                "}" +
                "})"
                )
    }

    @JavascriptInterface
    override fun authTrigger(isSignUp: Boolean) {
        this.listener.onAuthTrigger(isSignUp)
    }

    @JavascriptInterface
    override fun onClose() {
        this.listener.onClose()
    }
}


