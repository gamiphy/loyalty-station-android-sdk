package com.gamiphy.loyaltyStation.webview

import androidx.annotation.RestrictTo
import androidx.annotation.RestrictTo.Scope
import com.gamiphy.loyaltyStation.models.User

@RestrictTo(Scope.LIBRARY)
interface WebViewActions {
    fun login(user: User)

    fun logout()

    fun close()

    fun refresh()

    fun share(text: String, link: String)
}