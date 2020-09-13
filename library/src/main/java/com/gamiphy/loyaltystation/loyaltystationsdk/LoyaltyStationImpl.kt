package com.gamiphy.loyaltystation.loyaltystationsdk

import android.content.Context
import androidx.annotation.RestrictTo
import com.gamiphy.loyaltystation.loyaltystationsdk.callback.OnAuthTrigger
import com.gamiphy.loyaltystation.jsintegration.JsIntegrationImp
import com.gamiphy.loyaltystation.loyaltystationsdk.models.CoreConfig
import com.gamiphy.loyaltystation.loyaltystationsdk.models.GamiphyData
import com.gamiphy.loyaltystation.loyaltystationsdk.models.GamiphyEnvironment
import com.gamiphy.loyaltystation.loyaltystationsdk.models.User
import com.gamiphy.loyaltystation.webview.GamiphyWebViewActivity

@RestrictTo(RestrictTo.Scope.LIBRARY)
class LoyaltyStationImpl : LoyaltyStation {
    private val gamiphyData = GamiphyData.getInstance()

    override fun init(context: Context, config: CoreConfig): LoyaltyStation {
        gamiphyData.config = config
        open(context)
        return this
    }

    override fun setEnvironment(env: GamiphyEnvironment) {
        gamiphyData.env = env
    }

    override fun open(context: Context) {
        context.startActivity(GamiphyWebViewActivity.newIntent(context))
    }

    override fun login(user: User) {
        GamiphyWebViewActivity.gamiphyWebViewActionsList.forEach {
            it.login(user)
        }
    }

    override fun logout(context: Context) {
        GamiphyWebViewActivity.gamiphyWebViewActionsList.forEach {
            it.logout()
        }
    }

    override fun close() {
        GamiphyWebViewActivity.gamiphyWebViewActionsList.forEach {
            it.close()
        }
    }

    override fun addOnAuthListener(onAuthTrigger: OnAuthTrigger) {
        JsIntegrationImp.onAuthTriggerListeners.add(onAuthTrigger)
    }
}