package com.gamiphy.loyaltyStation.webview

import com.gamiphy.loyaltyStation.models.User

data class WebViewConfig(
    var app: String,
    var user: User? = null
)