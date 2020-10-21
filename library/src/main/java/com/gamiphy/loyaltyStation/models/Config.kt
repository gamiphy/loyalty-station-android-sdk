package com.gamiphy.loyaltyStation.models

data class Config(
    var app: String,
    var user: User? = null,
    var agent: String? = null,
    var prefLang: String? = null
)