package com.example.projectcompose.Repository

import com.example.projectcompose.Network.ApiService
import com.google.firebase.ktx.Firebase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class  Repository @Inject constructor(private val networkService:ApiService,private val firebase:Firebase){
        fun getRemoteControl():ApiService  =  networkService
        fun getFirebase():Firebase  = firebase

}