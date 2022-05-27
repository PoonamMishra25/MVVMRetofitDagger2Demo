package com.example.mvvmretofitdagger2demo.retrofit

import com.example.mvvmretofitdagger2demo.model.PokemonDetailModel
import com.example.mvvmretofitdagger2demo.model.PokemonDetailModelItem
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

   // https://pokeapi.glitch.me/v1/pokemon/1
    @GET(pokemonNumber)
    suspend fun getPokemon():Response<PokemonDetailModel>
    companion object{
        const val pokemonNumber:String = "pokemon/8"
    }

}