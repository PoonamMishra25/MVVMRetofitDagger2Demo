package com.example.mvvmretofitdagger2demo.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon_details")
data class PokemonDb(
    @PrimaryKey(autoGenerate = false)
    var id:Int,
    var name:String,
    var type1:String,
    var image:String,
    var pokeId:Int

){
   // constructor(name: String,type1: String,image: String,pokeId: Int): this(this.id,name, type1,image,pokeId) {  }
}
