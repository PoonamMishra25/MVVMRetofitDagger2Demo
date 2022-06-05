package com.example.mvvmretofitdagger2demo.retrofit

import com.example.mvvmretofitdagger2demo.model.DetailCardsModel
import com.example.mvvmretofitdagger2demo.model.PokemonCardModel
import com.example.mvvmretofitdagger2demo.model.PokemonDetailModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiService {

   // https://pokeapi.glitch.me/v1/pokemon/457
    //https://api.pokemontcg.io/v2/cards?q=name:pikachu
    @GET("pokemon/{number}")
    suspend fun getPokemon(
       @Path("number") numb:Int
    ):Response<PokemonDetailModel>


    //Api key-"c23d725b-b816-4885-94d7-384f00774e9c"
    @GET
    suspend fun getCardDetials(@Url cardUrl:String
    ):Response<PokemonCardModel>
}