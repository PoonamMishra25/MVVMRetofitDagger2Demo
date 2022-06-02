package com.example.mvvmretofitdagger2demo.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mvvmretofitdagger2demo.model.PokemonDb
import com.example.mvvmretofitdagger2demo.model.PokemonDetailModelItem
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {

//    @Insert
//    suspend fun addPokemon(pokemon:PokemonDetailModelItem)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPokemonDB(pokemon:PokemonDb)

    @Query("Select type1 from pokemon_details order by id Asc")
    suspend fun getPokemonType():List<String>

    @Query("select * from pokemon_details where type1 like '%' || :type || '%'")
     fun getSpecificPokemon1(type:String): Flow<List<PokemonDb>>

    @Query("select * from pokemon_details where type1 like '%' || :type || '%'")
    fun getSpecificPokemon(type:String): List<PokemonDb>
    //abstract fun getSpecificPokemon(): List<PokemonDb>
//
//    @Query("Select name from pokemon_details where types = :types")
//    suspend fun getSpecificPokemon(types:String):List<String>
}