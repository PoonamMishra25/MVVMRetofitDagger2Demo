package com.example.mvvmretofitdagger2demo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmretofitdagger2demo.DetailsOfPokemon
import com.example.mvvmretofitdagger2demo.model.PokemonDb
import com.example.mvvmretofitdagger2demo.model.PokemonDetailModelItem
import com.example.mvvmretofitdagger2demo.repository.PokemonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel(private val repository: PokemonRepository):ViewModel() {

    val pokemonLiveData: LiveData<PokemonDetailModelItem>
    get() = repository.pokemonSer

    val pokemonSpecificLiveData: LiveData<List<PokemonDb>>
        get() = repository.pokemonSpecificType

    init {
        viewModelScope.launch(Dispatchers.IO) {
           // repository.getPokemon()
            repository.getPokemonService(DetailsOfPokemon.pokeId)
            repository.pokemonSer
           // repository.getPokemonTypes("Normal")
          getSpecificPoke("Grass")
           // repository.getPokemonTypes("Normal")
            repository.pokemonSpecificType
        }
    }
     fun getSpecificPoke(type:String){
        repository.getPokemonTypes(type)
    }

}