package com.gamiphy.loyaltyStation

import android.content.Context
import android.content.Intent
import com.gamiphy.loyaltyStation.models.Config
import com.gamiphy.loyaltyStation.models.Listener
import com.gamiphy.loyaltyStation.models.User
import com.gamiphy.loyaltyStation.webview.WebViewActivity
import com.gamiphy.loyaltyStation.webview.WebViewConfig

class LoyaltyStation {
    companion object {
        private lateinit var webViewIntent: Intent

        fun init(context: Context, config: Config, listener: Listener) {
            WebViewActivity.init(WebViewConfig(config.app, config.user), listener)

            webViewIntent = Intent(context, WebViewActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            }

            context.startActivity(webViewIntent)
        }

        fun open(context: Context) {
            context.startActivity(webViewIntent)
        }

        fun close() {
            WebViewActivity.actionsList.forEach {
                it.close()
            }
        }

        fun login(user: User) {
            WebViewActivity.actionsList.forEach {
                it.login(user)
            }
        }

        fun logout() {
            WebViewActivity.actionsList.forEach {
                it.logout()
            }
        }
    }
}