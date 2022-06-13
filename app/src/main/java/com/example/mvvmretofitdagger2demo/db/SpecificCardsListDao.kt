package com.example.mvvmretofitdagger2demo.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mvvmretofitdagger2demo.model.SavedCardModel

@Dao
interface SpecificCardsList {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCards(savedCardModel: SavedCardModel)

    @Query("Select * from deckCardList ")
    suspend fun getAllSpecificCards():List<SavedCardModel>
}