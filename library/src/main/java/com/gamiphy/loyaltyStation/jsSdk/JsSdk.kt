package com.gamiphy.loyaltyStation.jsSdk

import android.webkit.JavascriptInterface
import com.gamiphy.loyaltyStation.jsSdk.models.JsSdkConfig
import com.gamiphy.loyaltyStation.models.Listener

interface JsSdk {
    var config: JsSdkConfig
    var listener: JsListener

    fun getUrl(): String
    fun getInitScript(): String

    @JavascriptInterface
    fun authTrigger(isSignUp: Boolean)
    @JavascriptInterface
    fun onClose()

    companion object {
        var JAVASCRIPT_OBJ = "javascript_obj"
    }
}