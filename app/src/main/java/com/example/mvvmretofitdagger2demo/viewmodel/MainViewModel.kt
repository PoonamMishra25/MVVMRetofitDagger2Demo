package com.example.mvvmretofitdagger2demo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmretofitdagger2demo.DetailsOfPokemon
import com.example.mvvmretofitdagger2demo.model.DeckListModel
import com.example.mvvmretofitdagger2demo.model.PokemonCardModel
import com.example.mvvmretofitdagger2demo.model.PokemonDb
import com.example.mvvmretofitdagger2demo.model.PokemonDetailModelItem
import com.example.mvvmretofitdagger2demo.repository.PokemonRepository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: PokemonRepository):ViewModel() {

    val pokemonLiveData: LiveData<PokemonDetailModelItem>
    get() = repository.pokemonSer

    val pokemonSpecificLiveData: LiveData<List<PokemonDb>>
        get() = repository.pokemonSpecificType

    val deckListLiveData: LiveData<List<String>>
        get() = repository.deckLists

    val deckCardsLiveData: LiveData<List<String>>
    get() = repository.deckCardLists

    val pokemonCardLiveData: LiveData<PokemonCardModel>
        get() = repository.cardPokemon
    init {
        viewModelScope.launch {
           // repository.getPokemon()
            repository.getDeckLists()
//
//getdeckCardList(2)
            repository.getPokemonService(DetailsOfPokemon.pokeId)
            repository.pokemonSer
            repository.getPokemonCardService(DetailsOfPokemon.pokeName)
            repository.cardPokemon
          //  println("${Thread.currentThread()}"+"view model123")
           // repository.getPokemonTypes("Normal")
        //  getSpecificPoke("Grass")
           // repository.getPokemonTypes("Normal")
          //  repository.pokemonSpecificType
        }
    }

     suspend fun getdeckCardList(deckId:Int){
          repository.getDeckCardLists(deckId)

    }

}