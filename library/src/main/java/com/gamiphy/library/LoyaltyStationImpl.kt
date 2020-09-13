package com.gamiphy.library

import android.content.Context
import androidx.annotation.RestrictTo
import com.gamiphy.library.callback.OnAuthTrigger
import com.gamiphy.library.helper.JavaScriptInterfaceHelper
import com.gamiphy.library.models.CoreConfig
import com.gamiphy.library.models.GamiphyData
import com.gamiphy.library.models.GamiphyEnvironment
import com.gamiphy.library.models.User
import com.gamiphy.library.webview.GamiphyWebViewActivity

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
        JavaScriptInterfaceHelper.onAuthTriggerListeners.add(onAuthTrigger)
    }
}