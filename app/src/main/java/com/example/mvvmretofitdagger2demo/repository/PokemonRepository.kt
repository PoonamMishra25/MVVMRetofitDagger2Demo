package com.example.mvvmretofitdagger2demo.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvmretofitdagger2demo.db.PokemonDatabase
import com.example.mvvmretofitdagger2demo.model.*
import com.example.mvvmretofitdagger2demo.retrofit.ApiService
import com.example.mvvmretofitdagger2demo.utils.Constants
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

    private val _pokemonSer = MutableLiveData<PokemonDetailModelItem>()
    val pokemonSer: LiveData<PokemonDetailModelItem>
        //read only
        get() = _pokemonSer

    private val _cardPokemon = MutableLiveData<PokemonCardModel>()
    val cardPokemon :LiveData<PokemonCardModel>
    get() = _cardPokemon

    private val _allDeckList = MutableLiveData<List<DeckListModel>>()
    val deckLists :LiveData<List<DeckListModel>>
        get() = _allDeckList

    private val _pokemonSpecificType = MutableLiveData<List<PokemonDb>>()
    val pokemonSpecificType: LiveData<List<PokemonDb>>
        get() = _pokemonSpecificType

    suspend fun getDeckLists(){
        _allDeckList.postValue(pokemonDatabase.deckListDao().getAllLists())
    }


    suspend fun getPokemonService(numb:Int) {
        coroutineScope {
            launch {

                val result = apiService.getPokemon(numb)
                if (result.isSuccessful && result.body() != null) {
                    _pokemonSer.postValue(result.body()!!.get(0))
                   // _allPokemon.postValue(result.body()[0].name)
                }

            }
        }

    }

    suspend fun getPokemonCardService(pokemonName :String) {
//        coroutineScope {
//            launch {

                val result = apiService.getCardDetials(Constants.BASE_URL2+pokemonName)
                if (result.isSuccessful && result.body() != null) {
                    _cardPokemon.postValue(result.body()!!)
                }else{
                   Log.d("****","Error Occurred!")
                }

//            }
//        }

    }
    suspend fun getPokemon() {
        coroutineScope {
            launch {
                for (i in 1..300) {
                    val result = apiService.getPokemon(i)
                    if (result.isSuccessful && result.body() != null) {

                        val list: ArrayList<String> = ArrayList()
                        list.addAll(result.body()!![0].types)

                        val typesString = list.joinToString { "\'${it}\'" }

                        pokemonDatabase.pokemonDao().addPokemonDB(
                            PokemonDb(
                                i,
                                result.body()!![0].name,
                                typesString,
                                result.body()!![0].sprite,
                                result.body()!![0].family.id
                            )
                        )
                        // _pokemon.postValue(result.body()!!.get(0))
                    }

                }
            }

        }

    }

//     fun getPokemonTypes(type:String){
//       return _pokemonSpecificType.postValue( pokemonDatabase.pokemonDao().getSpecificPokemon(type))
//
//    }

}