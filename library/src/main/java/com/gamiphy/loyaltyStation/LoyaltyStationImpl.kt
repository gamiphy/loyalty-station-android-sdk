package com.gamiphy.loyaltyStation

import android.content.Context
import androidx.annotation.RestrictTo
import com.gamiphy.loyaltyStation.models.Config
import com.gamiphy.loyaltyStation.models.Listener
import com.gamiphy.loyaltyStation.models.User
import com.gamiphy.loyaltyStation.webview.WebViewActivity
import com.gamiphy.loyaltyStation.webview.WebViewConfig

@RestrictTo(RestrictTo.Scope.LIBRARY)
class LoyaltyStationImpl : LoyaltyStation {
    private lateinit var config: Config

    override fun init(context: Context, config: Config, listener: Listener): LoyaltyStation {
        WebViewActivity.init(WebViewConfig(config.app, config.user), listener);
        this.config = config;
        open(context)
        return this
    }

    override fun open(context: Context) {
        context.startActivity(WebViewActivity.newIntent(context))
    }

    override fun close() {
        WebViewActivity.actionsList.forEach {
            it.close()
        }
    }

    override fun login(user: User) {
        WebViewActivity.actionsList.forEach {
            it.login(user)
        }
    }

    override fun logout() {
        WebViewActivity.actionsList.forEach {
            it.logout()
        }
    }
}