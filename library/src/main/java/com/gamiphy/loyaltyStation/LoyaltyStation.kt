package com.gamiphy.loyaltyStation

import android.content.Context
import android.content.Intent
import com.gamiphy.loyaltyStation.models.OnAuthTriggerListener
import com.gamiphy.loyaltyStation.models.User
import com.gamiphy.loyaltyStation.webview.WebViewActivity

/**
 * Gamiphy Loyalty Station sdk
 */
class LoyaltyStation {
    /** WebView intent **/
    private var webViewIntent: Intent? = null

    /**
     * Initialize Gamiphy loyalty station app
     *
     * @param context Application context
     */
    fun initialize(context: Context) {
        val app = Config.instance.app;

        //App should be provided before initialize
        if (app != null) {
            this.webViewIntent = Intent(context, WebViewActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            }

            //Start the activity
            context.startActivity(this.webViewIntent)
        } else {
            throw Exception("[Loyalty Station] App id not set")
        }
    }

    companion object {
        private var instance: LoyaltyStation = LoyaltyStation();

        /**
         * Set loyalty station app id
         *
         * @param app loyalty station app id
         */
        fun setApp(app: String): Companion {
            Config.instance.app = app

            return LoyaltyStation
        }

        /**
         * Set loyalty station user object
         *
         * @param user logged in user data
         */
        fun setUser(user: User): Companion {
            Config.instance.user = user

            return LoyaltyStation
        }

        /**
         * Set loyalty station language
         *
         * @param language preferred language to show
         */
        fun setLanguage(language: String): Companion {
            Config.instance.language = language

            return LoyaltyStation
        }

        /**
         * Set custom agent
         *
         * @param agent custom ui key - provided by Gamiphy team
         */
        fun setAgent(agent: String): Companion {
            Config.instance.agent = agent

            return LoyaltyStation
        }

        /**
         * Set custom agent
         *
         * @param sandbox is sandbox enabled
         */
        fun setSandbox(sandbox: Boolean): Companion {
            Config.instance.sandbox = sandbox

            return LoyaltyStation
        }

        /**
         * set a callback to be invoked when the loyalty station requires login/sign up for the user
         *
         * @param onAuthTriggerListener a callback to be invoked when the loyalty station requires login/sign up for the user
         */
        fun setOnAuthTriggerListener(onAuthTriggerListener: OnAuthTriggerListener): Companion {
            Config.instance.onAuthTriggerListener = onAuthTriggerListener

            return LoyaltyStation
        }

        /**
         * Initialize Gamiphy loyalty station app
         *
         * @param context Application context
         */
        fun initialize(context: Context) {
            this.instance.initialize(context)
        }

        /**
         * Open loyalty station activity
         *
         * @param context Application context
         */
        fun open(context: Context) {
            context.startActivity(this.instance.webViewIntent)
        }

        /**
         * Close loyalty station activity
         */
        fun close() {
            this.instance.webViewIntent
            Config.instance.actionsList.forEach {
                it.close()
            }
        }

        /**
         * Login user to the loyalty station
         *
         * @param user User data
         */
        fun login(user: User) {
            Config.instance.user = user;

            Config.instance.actionsList.forEach {
                it.login(Config.instance.user!!)
            }
        }

        /**
         * Logout user from the loyalty station
         */
        fun logout() {
            Config.instance.actionsList.forEach {
                it.logout()
            }
        }
    }
}