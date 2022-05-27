package com.example.mvvmretofitdagger2demo

import android.app.Application

import com.example.mvvmretofitdagger2demo.di.ApplicationComponent
import com.example.mvvmretofitdagger2demo.di.DaggerApplicationComponent


class PokemonApplication: Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
       // applicationComponent= Dagge
       applicationComponent = DaggerApplicationComponent.builder().build()
    }
}