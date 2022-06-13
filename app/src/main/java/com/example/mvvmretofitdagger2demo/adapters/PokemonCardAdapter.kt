package com.example.mvvmretofitdagger2demo.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvmretofitdagger2demo.R

import com.example.mvvmretofitdagger2demo.databinding.CardListBinding
import com.example.mvvmretofitdagger2demo.model.DetailCardsModel


class PokemonCardAdapter(
    private val urlList: ArrayList<DetailCardsModel>,
    private val openDetails: (DetailCardsModel,Int) -> Unit
) :
    RecyclerView.Adapter<PokemonCardAdapter.PokemonViewHolder>() {

    inner class PokemonViewHolder(private val binding: CardListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(pokemonCardModel: DetailCardsModel,position:Int) {

            Glide.with(binding.ivCardImage).load(pokemonCardModel.images.small)
                .error(R.drawable.poke_ball).into(binding.ivCardImage)

            binding.root.setOnClickListener {
                openDetails(pokemonCardModel,position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {

        return PokemonViewHolder(
            CardListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {

        holder.bind(urlList[position],position)
    }

    override fun getItemCount(): Int {
        return urlList.size
    }
}