package com.gamiphy.loyaltyStation

import android.content.Context
import android.content.Intent
import com.gamiphy.loyaltyStation.models.OnAuthTriggerListener
import com.gamiphy.loyaltyStation.models.User
import com.gamiphy.loyaltyStation.webview.WebViewActivity
import com.gamiphy.loyaltyStation.webview.WebViewConfig

/**
 * Gamiphy Loyalty Station sdk
 */
class LoyaltyStation {
    /** WebView intent **/
    private var webViewIntent: Intent? = null

    /** Loyalty station app id **/
    private var app: String? = null

    /** Logged in user data **/
    private var user: User? = null

    /** Gamiphy agent key **/
    private var agent: String? = null

    /** Preferable language **/
    private var language: String? = null

    /** Listen to the loyalty station when login/sign up for the user **/
    private var onAuthTriggerListener: OnAuthTriggerListener? = null

    /**
     * Initialize Gamiphy loyalty station app
     *
     * @param context Application context
     */
    fun initialize(context: Context) {
        val app = this.app;

        if (app != null) {
            WebViewActivity.init(
                config = WebViewConfig(
                    app = app,
                    agent = this.agent,
                    user = this.user,
                    prefLang = this.language
                ),
                onAuthTriggerListener = this.onAuthTriggerListener
            )

            this.webViewIntent = Intent(context, WebViewActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            }

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
            this.instance.app = app

            return LoyaltyStation
        }

        /**
         * Set loyalty station user object
         *
         * @param user logged in user data
         */
        fun setUser(user: User): Companion {
            this.instance.user = user

            return LoyaltyStation
        }

        /**
         * Set loyalty station language
         *
         * @param language preferred language to show
         */
        fun setLanguage(language: String): Companion {
            this.instance.language = language

            return LoyaltyStation
        }

        /**
         * Set custom agent
         *
         * @param agent custom ui key - provided by Gamiphy team
         */
        fun setAgent(agent: String): Companion {
            this.instance.agent = agent

            return LoyaltyStation
        }

        /**
         * set a callback to be invoked when the loyalty station requires login/sign up for the user
         *
         * @param onAuthTriggerListener a callback to be invoked when the loyalty station requires login/sign up for the user
         */
        fun setOnAuthTriggerListener(onAuthTriggerListener: OnAuthTriggerListener): Companion {
            this.instance.onAuthTriggerListener = onAuthTriggerListener

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
            WebViewActivity.actionsList.forEach {
                it.close()
            }
        }

        /**
         * Login user to the loyalty station
         *
         * @param user User data
         */
        fun login(user: User) {
            WebViewActivity.actionsList.forEach {
                it.login(user)
            }
        }

        /**
         * Logout user from the loyalty station
         */
        fun logout() {
            WebViewActivity.actionsList.forEach {
                it.logout()
            }
        }
    }
}