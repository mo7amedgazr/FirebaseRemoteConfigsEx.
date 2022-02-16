package com.app.firebaseconfigs.utils

import com.app.firebaseconfigs.data.entity.AppConfigs
import java.util.*

object AppUtils {

    fun getMaintenanceMessage(appConfigs: AppConfigs): String? {
        val displayLanguage = Locale.getDefault().displayLanguage
        return if (displayLanguage.equals("English")) appConfigs.messageEn else appConfigs.messageAr
    }
}