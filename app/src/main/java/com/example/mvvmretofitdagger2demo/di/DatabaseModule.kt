package com.example.mvvmretofitdagger2demo.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.mvvmretofitdagger2demo.db.PokemonDao
import com.example.mvvmretofitdagger2demo.db.PokemonDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun getPokemonTypeDao(database: PokemonDatabase):PokemonDao{
        return database.pokemonDao()
    }
    @Singleton
    @Provides
    fun providePokemonDB(context:Context):PokemonDatabase{
        return Room.databaseBuilder(context,PokemonDatabase::class.java,
            "pokemonCard")
           // .createFromAsset("database/pokemonDb1.db")
          .addMigrations(MIGRATION_2_3,MIGRATION_3_4)
            .build()
    }
    val MIGRATION_2_3: Migration = object : Migration(2,3) {
        override fun migrate(database: SupportSQLiteDatabase) {

            database.execSQL(
                "CREATE TABLE if not exists `deckLists` (`deckListId` INTEGER, "
                        + "`deckListName` TEXT , PRIMARY KEY(`deckListId`))"
            )
        }
    }
    val MIGRATION_3_4: Migration = object : Migration(3,4) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL(
                "CREATE TABLE if not exists `deckCardList` (`deckCardId` INTEGER, "
                        + "`deckUrl` TEXT, "+
                " `deckListId` INTEGER ,PRIMARY KEY(`deckCardId`))"
            )

        }
    }

}