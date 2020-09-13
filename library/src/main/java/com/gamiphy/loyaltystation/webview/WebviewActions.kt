package com.gamiphy.loyaltystation.webview

import androidx.annotation.RestrictTo
import androidx.annotation.RestrictTo.Scope
import com.gamiphy.loyaltystation.loyaltystationsdk.models.User

@RestrictTo(Scope.LIBRARY)
interface WebviewActions {

    fun login(user: User)

    fun logout()

    fun close()

    fun refresh()
}