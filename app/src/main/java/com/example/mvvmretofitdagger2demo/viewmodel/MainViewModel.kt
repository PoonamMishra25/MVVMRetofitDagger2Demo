package com.example.mvvmretofitdagger2demo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmretofitdagger2demo.model.PokemonDb
import com.example.mvvmretofitdagger2demo.model.PokemonDetailModelItem
import com.example.mvvmretofitdagger2demo.repository.PokemonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel(private val repository: PokemonRepository):ViewModel() {

    val pokemonLiveData: LiveData<PokemonDetailModelItem>
    get() = repository.pokemon

    val pokemonSpecificLiveData: LiveData<List<PokemonDb>>
        get() = repository.pokemonSpecificType

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getPokemon()
            repository.pokemon
           // repository.getPokemonTypes("Normal")
            getSpecificPoke("Grass")
            repository.pokemonSpecificType
        }
    }
    suspend fun getSpecificPoke(type:String){
        repository.getPokemonTypes(type)
    }

}