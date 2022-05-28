package com.example.mvvmretofitdagger2demo.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvmretofitdagger2demo.db.PokemonDatabase
import com.example.mvvmretofitdagger2demo.model.PokemonDb
import com.example.mvvmretofitdagger2demo.model.PokemonDetailModelItem
import com.example.mvvmretofitdagger2demo.retrofit.ApiService
import javax.inject.Inject

class PokemonRepository @Inject constructor(private val apiService: ApiService,private val pokemonDatabase: PokemonDatabase) {

    private val _pokemon = MutableLiveData<PokemonDetailModelItem>()
    val pokemon: LiveData<PokemonDetailModelItem>//read only
        get() = _pokemon

    suspend fun getPokemon() {
        val result = apiService.getPokemon()
        if (result.isSuccessful && result.body() != null) {
            pokemonDatabase.pokemonDao().addPokemonDB(PokemonDb(
                result.body()!![0].family.id,result.body()!![0].name
          ,result.body()!![0].sprite))
            _pokemon.postValue(result.body()!!.get(0))
        }

    }


}