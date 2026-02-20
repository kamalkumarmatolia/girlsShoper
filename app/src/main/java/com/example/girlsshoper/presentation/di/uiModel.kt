package com.example.girlsshoper.presentation.di

import com.example.girlsshoper.data.repoImpl.repoImpl
import com.example.girlsshoper.domain.repo.repo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.messaging.FirebaseMessaging
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object uiModel {

    @Provides
    @Singleton
    fun providesRepo(
        firebaseFirestore: FirebaseFirestore,
        firebaseAuth: FirebaseAuth,
        firebaseMessaging: FirebaseMessaging
    ) : repo {
        return repoImpl(
            firebaseFirestore = firebaseFirestore,
            firebaseAuth = firebaseAuth,
            firebaseMessaging = firebaseMessaging
        )
    }
}