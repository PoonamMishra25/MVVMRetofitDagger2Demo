package com.example.mvvmretofitdagger2demo.views

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import com.example.mvvmretofitdagger2demo.R
import com.example.mvvmretofitdagger2demo.databinding.CardListBinding
import com.example.mvvmretofitdagger2demo.model.DetailCardsModel
import com.example.mvvmretofitdagger2demo.model.PokemonCardModel


class PokemonCardAdapter(private val urlList:ArrayList<DetailCardsModel>, val context: Context):
    RecyclerView.Adapter<PokemonCardAdapter.PokemonViewHolder>() {

    inner class PokemonViewHolder(private val binding: CardListBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(pokemonCardModel:DetailCardsModel) {

Glide.with(binding.ivCardImage).load(pokemonCardModel.images.small).into(binding.ivCardImage)
    }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {

        return PokemonViewHolder(CardListBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {

        holder.bind(urlList[position])
    }

    override fun getItemCount(): Int {
       return urlList.size
    }
}