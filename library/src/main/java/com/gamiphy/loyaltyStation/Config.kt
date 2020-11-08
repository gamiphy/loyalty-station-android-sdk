package com.gamiphy.loyaltyStation

import com.gamiphy.loyaltyStation.models.*
import com.gamiphy.loyaltyStation.webview.WebViewActions

class Config private constructor() {
    /** Loyalty station app id **/
    var app: String? = null

    /** Logged in user data **/
    var user: User? = null

    /** Gamiphy agent key **/
    var agent: String? = null

    /** Preferable language **/
    var language: String? = null

    /** Sandbox enabled **/
    var sandbox: Boolean? = false

    /** Loyalty station actions list **/
    var actionsList = mutableListOf<WebViewActions>()

    /** Listen to the loyalty station when login/sign up for the user **/
    var onAuthTriggerListener: OnAuthTriggerListener? = null

    /** Listen to the loyalty station when close button clicked **/
    var onShareListener: OnShareListener

    /** Listen to the loyalty station when close button clicked **/
    var onCloseListener: OnCloseListener

    init {
        onShareListener = object : OnShareListener {
            override fun onShare(text: String, link: String) {
                actionsList.forEach {
                    it.share(text, link)
                }
            }
        }
        onCloseListener = object : OnCloseListener {
            override fun onClose() {
                actionsList.forEach {
                    it.close()
                }
            }
        }
    }

    companion object {
        var instance: Config = Config()
    }
}