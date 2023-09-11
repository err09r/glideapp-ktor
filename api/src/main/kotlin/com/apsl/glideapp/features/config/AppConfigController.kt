package com.apsl.glideapp.features.config

import com.apsl.glideapp.common.dto.AppConfigDto

object AppConfigController {

    fun getAppConfig() = runCatching {
        AppConfigDto(unlockDistance = GlideConfiguration.UNLOCK_DISTANCE)
    }
}