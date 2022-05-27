package com.example.mvvmretofitdagger2demo.model

import androidx.room.Entity


class PokemonDetailModel : ArrayList<PokemonDetailModelItem>()

//@Entity(tableName = "pokemon_details")
data class PokemonDetailModelItem(
   val abilities: Abilities,
    val description: String,
    val family: Family,
    val gen: Int,
   val gender: List<Double>,
    val height: String,
    val legendary: Boolean,
    val mega: Boolean,
    val mythical: Boolean,
    val name: String,
    val number: String,
    val species: String,
    val sprite: String,
    val starter: Boolean,
   val types: List<String>,
    val ultraBeast: Boolean,
    val weight: String
){
    override fun toString(): String {
        return this.description
    }
}

data class Family(
    val evolutionLine: List<String>,
    val evolutionStage: Int,
    val id: Int
)
data class Abilities(
    val hidden: List<String>,
    val normal: List<String>
)