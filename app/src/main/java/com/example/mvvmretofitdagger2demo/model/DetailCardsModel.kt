package com.example.mvvmretofitdagger2demo.model

data class DetailCardsModel(
    val abilities: List<Ability>,
    val artist: String,
    val attacks: List<Attack>,
    val evolvesFrom: String,
    val evolvesTo: List<String>,
    val flavorText: String,
    val hp: String,
    val images: Images,
    val name: String,
    val resistances: List<Resistance>,
    val rules: List<String>,
    val types: List<String>,
    val weaknesses: List<Weaknesse>
)