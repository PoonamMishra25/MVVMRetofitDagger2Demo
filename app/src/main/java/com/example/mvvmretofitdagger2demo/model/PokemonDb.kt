package com.example.mvvmretofitdagger2demo.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon_details")
data class PokemonDb(
    @PrimaryKey(autoGenerate = false)
    var id:Int,
    var name:String,
   // var types:List<String>,
    var image:String
)