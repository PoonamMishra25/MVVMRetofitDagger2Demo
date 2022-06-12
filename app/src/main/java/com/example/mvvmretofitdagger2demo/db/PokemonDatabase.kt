package com.example.mvvmretofitdagger2demo.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mvvmretofitdagger2demo.model.DeckListModel
import com.example.mvvmretofitdagger2demo.model.PokemonDb
import com.example.mvvmretofitdagger2demo.model.SavedCardModel

@Database(entities = [PokemonDb::class,DeckListModel::class,SavedCardModel::class ], version = 4)
abstract class PokemonDatabase : RoomDatabase() {

    abstract fun pokemonDao(): PokemonDao
    abstract fun deckListDao(): DeckListDao
    abstract fun specificCardsList(): SpecificCardsList
 //

}

