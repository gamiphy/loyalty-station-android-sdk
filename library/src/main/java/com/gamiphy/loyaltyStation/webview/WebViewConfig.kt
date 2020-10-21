package com.gamiphy.loyaltyStation.webview

import com.gamiphy.loyaltyStation.models.User

data class WebViewConfig(
    var app: String,
    var agent: String? = null,
    var prefLang: String? = null,
    var user: User? = null
)