package com.app.firebaseconfigs.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import com.app.firebaseconfigs.data.FirebaseRemoteConfigRepository
import dagger.Provides

@Module
@InstallIn(SingletonComponent::class)
 class DataModule {
    @Singleton
    @Provides
     fun bindRemoteConfigsRepository(): FirebaseRemoteConfigRepository {
        val firebaseRemoteConfigRepository = FirebaseRemoteConfigRepository()
        firebaseRemoteConfigRepository.init()
        return firebaseRemoteConfigRepository
    }
}