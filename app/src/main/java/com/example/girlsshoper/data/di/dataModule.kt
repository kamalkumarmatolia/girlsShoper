package com.example.girlsshoper.data.di

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.storage.FirebaseStorage
import com.razorpay.Checkout
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object dataModule {

    @Provides
    @Singleton
    fun provideFirebaseStorage(): FirebaseFirestore{
        return FirebaseFirestore.getInstance()
    }

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth{
        return FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton
    fun provideCheckout(@ApplicationContext context: Context): Checkout {
        // Initialize and preload Razorpay Checkout
        Checkout.preload(context)
        return Checkout()
    }

    @Provides
    @Singleton
    fun providesFirebaseMessaging(): FirebaseMessaging{
        return FirebaseMessaging.getInstance()

    }

}