package com.example.mvvmretofitdagger2demo.model

import androidx.appcompat.app.AlertDialog
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mvvmretofitdagger2demo.R

@Entity(tableName = "deckCardList")
data class SavedCardModel(
    @PrimaryKey(autoGenerate = true)
    val deckCardId:Int ,
    val deckUrl:String,
    val deckListId:Int
) {
    constructor(deckUrl: String, deckListId: Int) : this(0, deckUrl, deckListId)

}
