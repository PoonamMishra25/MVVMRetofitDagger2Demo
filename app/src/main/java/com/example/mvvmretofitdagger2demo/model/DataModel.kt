package com.example.mvvmretofitdagger2demo.model

sealed class DataModel {
    data class TypeList(
        val typeOfList: ArrayList<String>
    ) : DataModel()

    data class PokemonList(
        val name: String,
        val image: String
    ) : DataModel()

    data class Friend(
        val name: String,
        val gender: String
    ) : DataModel()

    data class Colleague(
        val name: String,
        val organization: String,
        val designation: String
    ) : DataModel()
}
