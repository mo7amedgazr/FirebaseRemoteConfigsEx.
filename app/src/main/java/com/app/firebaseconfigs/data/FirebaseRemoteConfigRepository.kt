package com.app.firebaseconfigs.data

import androidx.lifecycle.MutableLiveData
import com.app.firebaseconfigs.R
import com.app.firebaseconfigs.data.entity.AppConfigs
import com.google.firebase.remoteconfig.FirebaseRemoteConfig

class FirebaseRemoteConfigRepository {
    private val instance = FirebaseRemoteConfig.getInstance()

    // Observe this instead of calling getMyString() method
//    val appConfigsLiveData: MutableLiveData<AppConfigs> = MutableLiveData()

    fun init() {
        // Set the default values locally
        instance.setDefaultsAsync(R.xml.remote_config_params)
//        appConfigsLiveData.value = getAppConfigs()
        instance.fetchAndActivate().addOnCompleteListener { task ->
            if (task.isSuccessful) {
//                appConfigsLiveData.value = getAppConfigs()
            } else {
                // Handle error
            }
        }
    }

     fun getAppConfigs(): AppConfigs {
        return AppConfigs(
            requiresUpdate = instance.getBoolean("requires_update"),
            messageEn = instance.getString("message_en"),
            messageAr = instance.getString("message_ar")
        )
    }
}