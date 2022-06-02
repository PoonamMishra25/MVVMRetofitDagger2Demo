package com.example.mvvmretofitdagger2demo.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import com.example.mvvmretofitdagger2demo.db.PokemonDatabase
import com.example.mvvmretofitdagger2demo.model.PokemonDb
import com.example.mvvmretofitdagger2demo.model.PokemonDetailModelItem
import com.example.mvvmretofitdagger2demo.retrofit.ApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val apiService: ApiService,
    private val pokemonDatabase: PokemonDatabase
) {

    private val _pokemon = MutableLiveData<PokemonDetailModelItem>()
    val pokemon: LiveData<PokemonDetailModelItem>
        //read only
        get() = _pokemon
    private val _pokemonSpecificType = MutableLiveData<List<PokemonDb>>()
    val pokemonSpecificType: LiveData<List<PokemonDb>>
        get() = _pokemonSpecificType

    suspend fun getPokemon() {
        coroutineScope {
            launch {
//               for(i in 6..500){
                val result = apiService.getPokemon(458)
                if (result.isSuccessful && result.body() != null) {

                    val list: ArrayList<String> = ArrayList()
                    list.addAll(result.body()!![0].types)

                    val typesString = list.joinToString { "\'${it}\'" }

//                       pokemonDatabase.pokemonDao().addPokemonDB(
//                           PokemonDb(i,
//                               result.body()!![0].name,
//                               typesString,
//                               result.body()!![0].sprite,
//                               result.body()!![0].family.id
//                           )
//                       )

                }
                _pokemon.postValue(result.body()!!.get(0))
            }
        }



    }



    suspend fun getPokemonTypes(type:String){
       return _pokemonSpecificType.postValue( pokemonDatabase.pokemonDao().getSpecificPokemon(type))

    }

}