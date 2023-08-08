package com.example.projectcompose.DI.Moduls

import android.content.SharedPreferences
import com.example.projectcompose.Network.ApiService
import com.google.firebase.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetWorkModul {
    @Provides
    @Singleton
    fun provideBaseUrl():String  = "https://e-shop-api-v3.onrender.com/"

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl:String,okHttpClient: OkHttpClient):Retrofit{
        return Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build()
    }

    @Singleton
    @Provides
    fun providNetworkService(retrofit: Retrofit):ApiService{
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideInterseptor():HttpLoggingInterceptor{
        val logging = HttpLoggingInterceptor()
        if(BuildConfig.DEBUG){
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        }
        else{
            logging.setLevel(HttpLoggingInterceptor.Level.NONE)
        }
        return logging
    }

    @Singleton
    @Provides
    fun provideOkkHttpClient(logging:HttpLoggingInterceptor):OkHttpClient{
         return OkHttpClient.Builder().addInterceptor(logging).build()
    }


}