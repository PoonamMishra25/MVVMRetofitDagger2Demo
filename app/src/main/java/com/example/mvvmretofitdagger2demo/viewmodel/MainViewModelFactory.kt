package com.example.mvvmretofitdagger2demo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmretofitdagger2demo.repository.PokemonRepository
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(private val repository:PokemonRepository) : ViewModelProvider.Factory
{

    override fun <T : ViewModel> create(modelClass: Class<T>): T
    {
    return MainViewModel(repository) as T
    }

}