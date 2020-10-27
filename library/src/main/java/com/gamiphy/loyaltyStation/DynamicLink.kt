package com.gamiphy.loyaltyStation

import android.content.Intent
import android.net.Uri
import android.util.Log
import com.google.firebase.dynamiclinks.ktx.dynamicLinks
import com.google.firebase.ktx.Firebase

/**
 * Responsible of handling the dynamic link parsing
 */
class DynamicLink internal constructor(intent: Intent) {
    private var referrer: String? = null

    companion object {
        private const val REFERRER_PARAM = "ls-referrer"
    }

    init {
        try {
            Firebase.dynamicLinks
                .getDynamicLink(intent)
                .addOnSuccessListener { pendingDynamicLinkData ->
                    // Get deep link from result (may be null if no link is found)
                    var deepLink: Uri? = null
                    if (pendingDynamicLinkData != null) {
                        deepLink = pendingDynamicLinkData.link;
                    }

                    if (deepLink != null) {
                        referrer = deepLink.getQueryParameter(REFERRER_PARAM)
                    }
                }
                .addOnFailureListener { e -> Log.w("[Testing]", "getDynamicLink:onFailure", e) }
        } catch (e: java.lang.Exception) {
            Log.e("[Loyalty Station]", e.message ?: "Couldn't read dynamic link params")
        }
    }

    fun getReferrer(): String? {
        return this.referrer
    }
}