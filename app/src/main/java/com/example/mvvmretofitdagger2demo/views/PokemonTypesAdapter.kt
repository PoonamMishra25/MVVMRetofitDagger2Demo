package com.example.mvvmretofitdagger2demo.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvmretofitdagger2demo.databinding.FragmentListOfPokemonsBinding
import com.example.mvvmretofitdagger2demo.databinding.ListItemBinding
import com.example.mvvmretofitdagger2demo.databinding.PokemonListBinding
import com.example.mvvmretofitdagger2demo.model.PokemonDb

class PokemonTypesAdapter(private val typeList: MutableList<PokemonDb> = mutableListOf()
,private val openDetails: (PokemonDb) -> Unit):RecyclerView.Adapter<PokemonTypesAdapter.PokemonTypeViewHolder>() {


    fun setPokeList(newList: List<PokemonDb>) {
        typeList.clear()
        typeList.addAll(newList)

    }
    inner class PokemonTypeViewHolder(private val binding:PokemonListBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(pokemonDb: PokemonDb){
        Glide.with(binding.ivListimageView).load(pokemonDb.image).into(binding.ivListimageView)
        binding.tvPokeName.text=pokemonDb.name

            binding.root.setOnClickListener{
                openDetails(pokemonDb)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonTypeViewHolder =
  PokemonTypeViewHolder(PokemonListBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: PokemonTypeViewHolder, position: Int) {
       holder.bind(typeList[position])
    }

    override fun getItemCount(): Int =typeList.size
}
