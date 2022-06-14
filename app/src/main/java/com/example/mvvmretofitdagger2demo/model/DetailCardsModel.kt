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

data class Ability(
    val name: String,
    val text: String,
    val type: String
)

data class Attack(
    val convertedEnergyCost: Int,
    val cost: List<String>,
    val damage: String,
    val name: String,
    val text: String
)

data class Images(
    val large: String,
    val small: String
)

data class Resistance(
    val type: String,
    val value: String
)

data class Weaknesse(
    val type: String,
    val value: String
)