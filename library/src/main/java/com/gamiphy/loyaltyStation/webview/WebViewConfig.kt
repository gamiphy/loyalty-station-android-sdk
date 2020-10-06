package com.gamiphy.loyaltyStation.webview

import com.gamiphy.loyaltyStation.models.Agents
import com.gamiphy.loyaltyStation.models.User

data class WebViewConfig(
    var app: String,
    var agent: Agents = Agents.Default,
    var user: User? = null
)