package com.example.mvvmretofitdagger2demo.di

import com.example.mvvmretofitdagger2demo.retrofit.ApiService
import com.example.mvvmretofitdagger2demo.utils.Constants
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {
   // https://pokeapi.glitch.me/v1/pokemon/1

    @Singleton
    @Provides
    fun provideRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }
    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit):ApiService{
        return retrofit.create(ApiService::class.java)
    }
    ////https://api.pokemontcg.io/v2/cards?q=name:pikachu
//    @Singleton
//    @Provides
//    fun provideCardRetrofit():Retrofit{
//        return Retrofit.Builder()
//            .baseUrl("https://api.pokemontcg.io/v2/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//
//    }
//    @Singleton
//    @Provides
//    fun provideCardApiService(retrofit: Retrofit):ApiService{
//        return retrofit.create(ApiService::class.java)
//    }
}