package com.app.firebaseconfigs.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.firebaseconfigs.data.FirebaseRemoteConfigRepository
import com.app.firebaseconfigs.data.entity.AppConfigs
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MaintenanceViewModel @Inject constructor(private val firebaseRemoteConfigRepository: FirebaseRemoteConfigRepository) :
    ViewModel() {

    val appConfigsLiveData = MutableLiveData<AppConfigs>()

    init {
        appConfigsLiveData.value = firebaseRemoteConfigRepository.getAppConfigs()
    }

}