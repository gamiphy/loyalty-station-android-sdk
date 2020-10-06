package com.gamiphy.loyaltyStation.jsSdk.models

import com.gamiphy.loyaltyStation.models.Agents
import com.gamiphy.loyaltyStation.models.Environments

data class JsSdkConfig(
    var initConfig: JsSdkInitConfig,
    var environment: Environments,
    var agent: Agents
)