package com.example.mvvmretofitdagger2demo.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mvvmretofitdagger2demo.model.SavedCardModel

@Dao
interface SpecificCardsListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCards(savedCardModel: SavedCardModel)

    @Query("Select deckUrl from deckCardList  where deckListId = :deckId")
    suspend fun getAllSpecificCards(deckId : Int):List<String>
}