package com.example.mvvmretofitdagger2demo.model

data class DetailCardsModel(
    val abilities: List<Ability>,
    val artist: String,
    val attacks: List<Attack>,
    val cardmarket: Cardmarket,

    val evolvesFrom: String,
    val evolvesTo: List<String>,
    val flavorText: String,
    val hp: String,
    val id: String,
    val images: Images,

    val level: String,
    val name: String,

    val number: String,
    val rarity: String,
    val regulationMark: String,
    val resistances: List<Resistance>,

    val rules: List<String>,

    val subtypes: List<String>,
    val supertype: String,
    val tcgplayer: Tcgplayer,
    val types: List<String>,
    val weaknesses: List<Weaknesse>
)