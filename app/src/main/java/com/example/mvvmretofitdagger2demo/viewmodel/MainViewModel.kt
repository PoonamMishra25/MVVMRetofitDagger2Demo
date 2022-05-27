package com.example.mvvmretofitdagger2demo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmretofitdagger2demo.model.PokemonDetailModelItem
import com.example.mvvmretofitdagger2demo.repository.PokemonRepository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: PokemonRepository):ViewModel() {

    val pokemonLiveData: LiveData<PokemonDetailModelItem>
    get() = repository.pokemon

    init {
        viewModelScope.launch {
            repository.getPokemon()
        }
    }
}