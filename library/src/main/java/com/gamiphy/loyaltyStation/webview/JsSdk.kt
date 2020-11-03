package com.gamiphy.loyaltyStation.webview

import android.webkit.JavascriptInterface
import com.gamiphy.loyaltyStation.Config
import com.gamiphy.loyaltyStation.models.User
import com.google.gson.Gson

data class InitializeConfig(
    var app: String,
    var user: User?,
    var prefLang: String?,
    var openByDefault: Boolean?,
    var barHidden: Boolean?,
    var hasShareHandler: Boolean?
)

class JsSdkImp {
    val JAVASCRIPT_OBJ = "javascript_obj"

    @JavascriptInterface
    fun authTrigger(isSignUp: Boolean) {
        Config.instance.onAuthTriggerListener?.onAuthTrigger(isSignUp)
    }

    @JavascriptInterface
    fun onShare(text: String, link: String) {
        Config.instance.onShareListener.onShare(text, link)
    }

    @JavascriptInterface
    fun onClose() {
        Config.instance.onCloseListener.onClose()
    }

    fun getInitScript(): String {
        val app = Config.instance.app ?: return "";

        val initConfig = Gson().toJson(
            InitializeConfig(
                app = app,
                user = Config.instance.user,
                prefLang = Config.instance.language,
                openByDefault = true,
                barHidden = true,
                hasShareHandler = true
            )
        ).toString()

        val json = initConfig.substring(1, initConfig.length - 1);

        return ("javascript: window.Gamiphy.init({\n" +
                "$json,\n" +
                "goToAuth: function (isSignUp) {" +
                JAVASCRIPT_OBJ + "." + JsSdkImp::authTrigger.name + "(JSON.stringify(isSignUp)); " +
                "}," +
                "shareHandler: function (link, text) {" +
                JAVASCRIPT_OBJ + "." + JsSdkImp::onShare.name + "(text, link); " +
                "}," +
                "onClose: function (event) {" +
                JAVASCRIPT_OBJ + "." + JsSdkImp::onClose.name + "(); " +
                "}" +
                "})"
                )
    }
}


