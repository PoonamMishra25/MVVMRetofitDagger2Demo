package com.example.mvvmretofitdagger2demo.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.mvvmretofitdagger2demo.model.PokemonDb
import com.example.mvvmretofitdagger2demo.model.PokemonDetailModelItem

@Dao
interface PokemonDao {

//    @Insert
//    suspend fun addPokemon(pokemon:PokemonDetailModelItem)

    @Insert
    suspend fun addPokemonDB(pokemon:PokemonDb)

//    @Query("Select types from pokemon_details")
//    suspend fun getPokemonType():List<String>
//
//    @Query("Select name from pokemon_details where types = :types")
//    suspend fun getSpecificPokemon(types:String):List<String>
}