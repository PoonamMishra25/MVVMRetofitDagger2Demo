package com.example.mvvmretofitdagger2demo.retrofit

import com.example.mvvmretofitdagger2demo.model.PokemonDetailModel
import com.example.mvvmretofitdagger2demo.model.PokemonDetailModelItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

   // https://pokeapi.glitch.me/v1/pokemon/457
    @GET("pokemon/{number}")
    suspend fun getPokemon(
       @Path("number") numb:Int
    ):Response<PokemonDetailModel>


}