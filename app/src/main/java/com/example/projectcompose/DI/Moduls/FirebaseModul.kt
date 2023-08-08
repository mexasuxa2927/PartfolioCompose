package com.example.projectcompose.DI.Moduls

import com.example.projectcompose.DI.App
import com.google.firebase.FirebaseApp
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FirebaseModul {

    @Singleton
    @Provides
    fun provideFirebase():Firebase{
        return Firebase
    }



}