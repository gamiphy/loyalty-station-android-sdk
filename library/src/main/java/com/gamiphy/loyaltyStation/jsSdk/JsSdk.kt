package com.gamiphy.loyaltyStation.jsSdk

import android.webkit.JavascriptInterface
import com.gamiphy.loyaltyStation.jsSdk.models.JsSdkConfig
import com.gamiphy.loyaltyStation.models.AuthListener

interface JsSdk {
    var config: JsSdkConfig
    var authListener: AuthListener?

    fun getUrl(): String
    fun getInitScript(): String

    @JavascriptInterface
    fun authTrigger(isSignUp: Boolean)

    companion object {
        var JAVASCRIPT_OBJ = "javascript_obj"
    }
}