package com.example.mvvmretofitdagger2demo.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvmretofitdagger2demo.model.PokemonDetailModelItem
import com.example.mvvmretofitdagger2demo.retrofit.ApiService
import javax.inject.Inject

class PokemonRepository @Inject constructor(private val apiService: ApiService) {

    private val _pokemon = MutableLiveData<PokemonDetailModelItem>()
    val pokemon: LiveData<PokemonDetailModelItem>//read only
        get() = _pokemon

    suspend fun getPokemon() {
        val result = apiService.getPokemon()
        if (result.isSuccessful && result.body() != null) {
            _pokemon.postValue(result.body()!!.get(0))
        }else{
            Log.d("***8","Failed response")
        }

    }


}