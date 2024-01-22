package com.apsl.glideapp.features.config

import com.apsl.glideapp.common.dto.AppConfigDto

class AppConfigController(private val glideConfig: GlideConfig) {

    fun getAppConfig() = runCatching {
        AppConfigDto(unlockDistance = glideConfig.unlockDistance)
    }
}
