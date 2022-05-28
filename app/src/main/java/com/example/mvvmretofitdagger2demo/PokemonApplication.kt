package com.example.mvvmretofitdagger2demo

import android.app.Application

import com.example.mvvmretofitdagger2demo.di.ApplicationComponent
import com.example.mvvmretofitdagger2demo.di.DaggerApplicationComponent

import com.example.mvvmretofitdagger2demo.repository.PokemonRepository



class PokemonApplication: Application() {

    lateinit var applicationComponent: ApplicationComponent
    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent.factory().create(this)
    }
}