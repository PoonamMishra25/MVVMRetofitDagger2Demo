package com.example.mvvmretofitdagger2demo.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mvvmretofitdagger2demo.model.DeckListModel
@Dao
interface DeckListDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addDeckLists(deckName:DeckListModel)

    @Query("select * from deckLists")
    suspend fun getAllLists():List<DeckListModel>

    @Query("select deckListId from deckLists where deckListName = :deckName ")
    suspend fun getIds(deckName: String):Int
}