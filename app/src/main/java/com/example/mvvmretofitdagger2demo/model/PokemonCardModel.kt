package com.example.mvvmretofitdagger2demo.model

data class PokemonCardModel(
    val count: Int,
    val `data`: List<DetailCardsModel>,
    val page: Int,
    val pageSize: Int,
    val totalCount: Int
)