package com.gamiphy.loyaltyStation.jsSdk.models

import com.gamiphy.loyaltyStation.models.User

data class JsSdkInitConfig(
    var app: String,
    var user: User? = null,
    var prefLang: String? = null,
    var openByDefault: Boolean? = true,
    var barHidden: Boolean? = true
)