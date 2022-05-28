package com.example.mvvmretofitdagger2demo.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mvvmretofitdagger2demo.model.PokemonDb

@Database(entities = [PokemonDb::class],version = 2)
abstract class PokemonDatabase :RoomDatabase() {

    abstract fun pokemonDao(): PokemonDao
}
//
//    companion object {
//
//        @Volatile
//        private var INSTANCE: PokemonDatabase? = null
//
//        fun getDatabase(context: Context): PokemonDatabase {
//            if (INSTANCE == null) {
//                synchronized(this) {
//                    INSTANCE = Room.databaseBuilder(
//                        context,
//                        PokemonDatabase::class.java,
//                        "PokemonDB"
//                    )
//                        .build()
//                }
//
//            }
//            return INSTANCE!!
//        }
//    }
