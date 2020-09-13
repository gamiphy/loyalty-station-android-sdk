package com.gamiphy.loyaltystation.jsintegration

import com.gamiphy.loyaltystation.loyaltystationsdk.models.CoreConfig
import com.gamiphy.loyaltystation.loyaltystationsdk.models.GamiphyData
import com.gamiphy.loyaltystation.loyaltystationsdk.models.GamiphyEnvironment
import com.google.gson.Gson

object Config {
    val BOT_API = when (GamiphyData.getInstance().env) {
        GamiphyEnvironment.DEV -> "https://sdk.dev.gamiphy.co"
        GamiphyEnvironment.PROD -> "https://sdk.gamiphy.co"
    }

    val BOT_SCRIPT = "<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "<head>\n" +
            "    <script src='$BOT_API'></script>\n" +
            "</head>\n" +
            "</html>"


    fun init(config: CoreConfig): String {
        val initConfig = Gson().toJson(config).toString()
        val json = initConfig.substring(1, initConfig.length - 1)
        return ("javascript: window.Gamiphy.init({\n" +
                "$json,\n" +
                "goToAuth: function (event) {" +
                JAVASCRIPT_OBJ + "." + JsIntegrationImp()::isLoggedIn.name + "(JSON.stringify(event)); " +
                "}" +
                "})"
                )
    }

    const val JAVASCRIPT_OBJ = "javascript_obj"
}
