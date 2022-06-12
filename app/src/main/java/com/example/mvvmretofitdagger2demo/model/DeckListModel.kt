package com.example.mvvmretofitdagger2demo.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "deckLists")
data class DeckListModel(

    @PrimaryKey(autoGenerate = true)
    val deckListId:Int,

    val deckListName:String
){
    constructor(deckListName: String) : this(0, deckListName)
}
