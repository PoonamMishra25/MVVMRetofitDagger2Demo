package com.example.mvvmretofitdagger2demo.di

import com.example.mvvmretofitdagger2demo.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])

interface ApplicationComponent {

   fun inject(mainActivity: MainActivity)
}