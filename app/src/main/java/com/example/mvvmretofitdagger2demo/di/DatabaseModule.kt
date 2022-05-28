package com.example.mvvmretofitdagger2demo.di

import android.content.Context
import androidx.room.Room
import com.example.mvvmretofitdagger2demo.db.PokemonDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun providePokemonDB(context:Context):PokemonDatabase{
        return Room.databaseBuilder(context,PokemonDatabase::class.java,"pokemonDb1").build()
    }
}