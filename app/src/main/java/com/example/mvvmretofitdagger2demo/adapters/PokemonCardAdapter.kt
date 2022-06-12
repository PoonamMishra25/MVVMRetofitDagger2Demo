package com.example.mvvmretofitdagger2demo.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import com.example.mvvmretofitdagger2demo.databinding.CardListBinding
import com.example.mvvmretofitdagger2demo.model.DetailCardsModel


class PokemonCardAdapter(private val urlList:ArrayList<DetailCardsModel>,private val openDetails: (DetailCardsModel) -> Unit):
    RecyclerView.Adapter<PokemonCardAdapter.PokemonViewHolder>() {

    inner class PokemonViewHolder(private val binding: CardListBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(pokemonCardModel:DetailCardsModel) {

Glide.with(binding.ivCardImage).load(pokemonCardModel.images.small).into(binding.ivCardImage)

            binding.root.setOnClickListener{
                openDetails(pokemonCardModel)
            }
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